package com.micro.boot.thirdparty.ucpaas.template;

/**
 * 〈Ucpaas发送短信内容模板〉
 *
 * @author Administrator
 * @create 2018/3/24
 * @since 1.0.0
 */
public class UcpaasTemplate {

    //type
    public String type;

    //template_name
    public String template_name;

    //autograph
    public String autograph;

    //content
    public String content;

    //page_num
    public String page_num;

    //page_size
    public String page_size;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTemplate_name() {
        return template_name;
    }

    public void setTemplate_name(String template_name) {
        this.template_name = template_name;
    }

    public String getAutograph() {
        return autograph;
    }

    public void setAutograph(String autograph) {
        this.autograph = autograph;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPage_num() {
        return page_num;
    }

    public void setPage_num(String page_num) {
        this.page_num = page_num;
    }

    public String getPage_size() {
        return page_size;
    }

    public void setPage_size(String page_size) {
        this.page_size = page_size;
    }
}