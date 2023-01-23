### CSDN MarkDown图片外链适配工具

---
### 解决问题：

MardDown 文档有 2 种格式的图片语法，即 `![alt](src)`与`<img src="url">`，但是 CSDN 只支持第一种格式的语法，因此如果我们将第二种格式的图片插入到博客，则会如下图一样无法显示。所以我们必须要将第二种格式的图片语法转化成第一种格式。

![](https://oss.thinkstu.com/typora/202301232031087.png?x-oss-process=style/optimize)

---

### 使用说明：

将想要转换格式的 md 文档复制到项目的`/resources`目录，然后在主程序中声明`文件名称`后启动，程序自动生成文件名带有`CSDN-`标识的适配后文档。
```java
imageTrasation("文件名称.md");
```

![](https://oss.thinkstu.com/typora/202301232036801.png?x-oss-process=style/optimize)

