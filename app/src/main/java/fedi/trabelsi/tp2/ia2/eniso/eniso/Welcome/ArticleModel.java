package fedi.trabelsi.tp2.ia2.eniso.eniso.Welcome;

public class ArticleModel {
    private String article;
    private String extra;
    private String body;
    private String writer;

    public ArticleModel(String art,String ex,String core,String auth){
        this.article=art;
        this.extra=ex;
        this.body=core;
        this.writer=auth;
    }

    public void setArticle(String article){
        this.article=article;
    }

    public String getArticle(){
        return this.article;
    }

    public void setExtra(String extra){
        this.extra=extra;
    }

    public String getExtra(){
        return this.extra;
    }

    public void setBody(String body){

        this.body=body;
    }

    public String getBody(){
        return this.body;
    }

    public void setWriter(String writer){
        this.writer=writer;
    }

    public String getWriter(){
        return this.writer;
    }

}
