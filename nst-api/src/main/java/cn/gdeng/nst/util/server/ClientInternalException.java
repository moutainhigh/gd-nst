/**
 * ClientInternalException.java
 *
 * Copyright @year@ @company@, Inc.
 *
 * @company@ licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package cn.gdeng.nst.util.server;

/**
 * @author yangjj
 */
public class ClientInternalException extends RuntimeException {

    private static final long serialVersionUID = -4878447156010807963L;

    public ClientInternalException() {
        super();
    }

    public ClientInternalException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClientInternalException(String message) {
        super(message);
    }

    public ClientInternalException(Throwable cause) {
        super(cause);
    }

}
