/**
 * Question.java
 *
 * Copyright 2012 Baidu, Inc.
 *
 * Baidu licenses this file to you under the Apache License, version 2.0
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
package cn.gdeng.nst.admin.dto.right;

import java.io.Serializable;

/**
 * 百度安全问题类
 * @author yangjj
 */
public class BaiDuQuestionDTO implements Serializable{

    private static final long serialVersionUID = -3818369427211829069L;
    //安全问题ID
    private int qid;
    //安全问题字面
    private String content;

    public int getQid() {
        return qid;
    }

    public void setQid(int qid) {
        this.qid = qid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
