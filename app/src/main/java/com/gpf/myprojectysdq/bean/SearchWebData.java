package com.gpf.myprojectysdq.bean;

/**
 * Created by Administrator on 2016/11/12.
 */

public class SearchWebData {
    /**
     * qq : http://v.qq.com/cover/r/relqwr6c0in3s5b.html?ptag=360kan.movie
     * tudou : http://www.tudou.com/albumplay/KE2zJh9AWII/2Otbf25W5h4.html?tpa=dW5pb25faWQ9MTAyMjEzXzEwMDAwMV8wMV8wMQ
     * youku : http://v.youku.com/v_show/id_XODMzOTcyNjg0.html?tpa=dW5pb25faWQ9MTAyMjEzXzEwMDAwN18wMV8wMQ
     * fengxing : http://www.fun.tv/vplay/m-115237.e-1?alliance=152055
     * qiyi : http://www.iqiyi.com/v_19rrn94g50.html?vfm=f_191_360y
     * baofeng : http://g.hd.baofeng.com/play/278/play-782278.html?fid=1324
     * pptv : http://v.pptv.com/show/lyibYF3iclVZP2dHg.html
     */

    private String qq;
    private String tudou;
    private String youku;
    private String fengxing;
    private String qiyi;
    private String baofeng;
    private String pptv;

    public SearchWebData() {
    }

    public SearchWebData(String qq, String qiyi, String baofeng, String pptv) {
        this.qq = qq;
        this.qiyi = qiyi;
        this.baofeng = baofeng;
        this.pptv = pptv;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getTudou() {
        return tudou;
    }

    public void setTudou(String tudou) {
        this.tudou = tudou;
    }

    public String getYouku() {
        return youku;
    }

    public void setYouku(String youku) {
        this.youku = youku;
    }

    public String getFengxing() {
        return fengxing;
    }

    public void setFengxing(String fengxing) {
        this.fengxing = fengxing;
    }

    public String getQiyi() {
        return qiyi;
    }

    public void setQiyi(String qiyi) {
        this.qiyi = qiyi;
    }

    public String getBaofeng() {
        return baofeng;
    }

    public void setBaofeng(String baofeng) {
        this.baofeng = baofeng;
    }

    public String getPptv() {
        return pptv;
    }

    public void setPptv(String pptv) {
        this.pptv = pptv;
    }
}
