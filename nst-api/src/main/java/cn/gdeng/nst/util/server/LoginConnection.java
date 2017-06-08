/**
 * LoginConnection.java Copyright 2012 Baidu, Inc. Baidu licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the
 * License at: http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language governing permissions and limitations
 * under the License.
 */
package cn.gdeng.nst.util.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.security.Key;

import org.apache.commons.lang3.ArrayUtils;

/**
 * @author yangjj
 */
public class LoginConnection extends JsonConnection {

    private static final int MAX_MSG_SIZE = 4096;

    private Key              publicKey;

    /**
     * @param url
     * @throws MalformedURLException
     * @throws IOException
     */
    public LoginConnection(String url) throws MalformedURLException, IOException {
        super(url);
    }

    /**
     * 向服务器发送信息
     *
     * @parambody 向服务器发送的信封对象
     */
    public void sendRequest(Object request, String uuid, String accountType) {
        OutputStream out = sendRequest(uuid, accountType);
        try {
            String json = JacksonUtil.obj2Str(request);
            byte[] zip = GZipUtil.gzipString(json);
            zip = RSAUtil.encryptByPublicKey(zip, publicKey);
            out.write(zip);
        } catch (Exception e) {
            throw new ClientInternalException(e);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    throw new ClientInternalException(e);
                }
            }
        }
    }

    public <T> T readSecClearResponse(Class<T> t) {
        InputStream in = readResponse();
        try {
            byte[] b = new byte[8];
            if (in.read(b) != 8) {
                throw new ClientInternalException("Server response is invalid.");
            }
            if (b[1] != 0) {
                throw new ClientInternalException("Server returned an error code: " + b[1]);
            }
            int total = 0, k = 0;
            b = new byte[MAX_MSG_SIZE];
            while (total < MAX_MSG_SIZE) {
                k = in.read(b, total, MAX_MSG_SIZE - total);
                if (k < 0) break;
                total += k;
            }
            if (total == MAX_MSG_SIZE) {
                throw new ClientInternalException("Server returned message too large.");
            }
            byte[] zip = ArrayUtils.subarray(b, 0, total);
            zip = GZipUtil.unGzip(zip);
            return JacksonUtil.str2Obj(new String(zip, "UTF-8"), t);
        } catch (IOException e) {
            throw new ClientInternalException(e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    throw new ClientInternalException(e);
                }
            }
        }
    }

    public void setPublicKey(Key publicKey) {
        this.publicKey = publicKey;
    }

}
