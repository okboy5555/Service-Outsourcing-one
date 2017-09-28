## 接口设计
### 用户模块
#### 用户注册
|Key|Value|
|:---:|:---:|
|地址|http://host:port/handle/register|
|数据格式|Json|
|访问方式|POST|

|发送数据|注释|
|:---:|:---:|
|username|用户名|
|password|密码|
|phone|手机号|
|mail|邮箱号|

|返回数据|注释|
|:---:|:---:|
|status|操作状态|
|message|消息|

#### 用户登录
|Key|Value|
|:---:|:---:|
|地址|http://host:port/handle/login|
|数据格式|Json|
|访问方式|POST|

|发送数据|注释|
|:---:|:---:|
|strLogin|邮箱、手机号、用户名|
|password|密码|
|type|1|

|返回数据|注释|
|:---:|:---:|
|status|操作状态|
|message|消息|
|token|用于身份验证|
#### 用户向客服提出问题
|Key|Value|
|:---:|:---:|
|地址|http://host:port/handle/getServiceURL|
|数据格式|Json|
|访问方式|POST|

|发送数据|注释|
|:---:|:---:|
|token|用于身份验证|

|返回数据|注释|
|:---:|:---:|
|status|操作状态|
|message|消息，成功则返回URL|

|Key|Value|
|:---:|:---:|
|地址|message（若getServiceURL成功返回）|
|数据格式|Json|
|访问方式|POST|

|发送数据|注释|
|:---:|:---:|
|str|用户发送给客服的字符串|
|token|用于身份验证|

|返回数据|注释|
|:---:|:---:|
|status|操作状态|
|message|消息|
#### 查看个人信息
|Key|Value|
|:---:|:---:|
|地址|http://host:port/handle/getUserInfo|
|数据格式|Json|
|访问方式|POST|

|发送数据|注释|
|:---:|:---:|
|token|用于身份验证|

|返回数据|注释|
|:---:|:---:|
|status|操作状态|
|message|消息|
#### 修改个人信息
|Key|Value|
|:---:|:---:|
|地址|http://host:port/handle/setUserInfo|
|数据格式|Json|
|访问方式|POST|

|发送数据|注释|
|:---:|:---:|
|token|用于身份验证|
|...|...|
(***需要实际讨论***)

|返回数据|注释|
|:---:|:---:|
|status|操作状态|
|message|消息|
#### 问题反馈
|Key|Value|
|:---:|:---:|
|地址|http://host:port/handle/callback|
|数据格式|Json|
|访问方式|POST|

|发送数据|注释|
|:---:|:---:|
|token|用于身份验证|
|subject|主题|
|detail|详细反馈信息|

|返回数据|注释|
|:---:|:---:|
|status|操作状态|
|message|消息|
### 客服模块
#### 客服登陆
|Key|Value|
|:---:|:---:|
|地址|http://host:port/handle/login|
|数据格式|Json|
|访问方式|POST|

|发送数据|注释|
|:---:|:---:|
|strLogin|邮箱、手机号、用户名|
|password|密码|
|type|2|

|返回数据|注释|
|:---:|:---:|
|status|操作状态|
|message|消息|
|token|用于身份验证|
#### 用户信息回复
|Key|Value|
|:---:|:---:|
|地址|http://host:port/handle/admin/getServiceURL|
|数据格式|Json|
|访问方式|POST|

|发送数据|注释|
|:---:|:---:|
|token|用于身份验证|

|返回数据|注释|
|:---:|:---:|
|status|操作状态|
|message|消息，成功则返回URL|

|Key|Value|
|:---:|:---:|
|地址|message（若getServiceURL成功返回）|
|数据格式|Json|
|访问方式|POST|

|发送数据|注释|
|:---:|:---:|
|str|用户发送给用户的字符串|
|token|用于身份验证|

|返回数据|注释|
|:---:|:---:|
|status|操作状态|
|message|消息|
#### 关键字回复
|Key|Value|
|:---:|:---:|
|地址|http://host:port/handle/admin/KeywordReply|
|数据格式|Json|
|访问方式|POST|

|发送数据|注释|
|:---:|:---:|
|opType|操作种类（C、U、R、D）|
|id|关键字id|
|keyword|关键字|
|content|关键字详细内容|
|token|用于身份验证|

|返回数据|注释|
|:---:|:---:|
|status|操作状态|
|message|消息|
*获取为获取到该客服自定义以及系统默认的回复列表，添加为该客服自定义回复*
### 管理员模块
#### 管理员登陆
|Key|Value|
|:---:|:---:|
|地址|http://host:port/handle/admin/login|
|数据格式|Json|
|访问方式|POST|

|发送数据|注释|
|:---:|:---:|
|strLogin|邮箱、手机号、用户名|
|password|密码|
|type|3|

|返回数据|注释|
|:---:|:---:|
|status|操作状态|
|message|消息|
|token|用于身份验证|
#### 删除用户
#### 查询用户信息
#### 修改用户信息
#### 添加客服
#### 删除客服
#### 查询客服信息
#### 修改客服信息
#### 查看用户反馈
#### 广告位
#### 添加管理员
#### 删除管理员
#### 查询管理员信息
#### 修改管理员信息
### 智能机器人
#### 用户提出问题
#### 用户信息回复
