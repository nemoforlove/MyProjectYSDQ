package com.gpf.myprojectysdq.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/11.
 */

public class SearchData {

    /**
     * title : 心花怒放
     * tag : 喜剧 / 爱情
     * act : 黄渤 徐峥 袁泉 周冬雨
     * year : 2014
     * rating : 7
     * area : 大陆
     * dir : 宁浩
     * desc : 耿浩（黄渤 饰）在偶遇“小三”危机，陷入情感困境。面对背叛，耿浩陷入了难以自拔的痛苦之中，好基友郝义（徐峥 饰）为了帮他摆脱痛苦，决定带他南下“猎艳”，遍访“百花”。于是两个“暴走兄弟”带上一只狗，开始了一段疯狂而搞笑的放浪旅途。一路上他们结识了各式女伴，并经历了一连串奇葩的遭遇。最后，两人最终明白了爱的真谛，并收获了彼此的幸福。
     * cover : http://p5.qhimg.com/t0171e200d2546740aa.jpg
     * vdo_status : play
     * playlinks : {"qq":"http://v.qq.com/cover/r/relqwr6c0in3s5b.html?ptag=360kan.movie","tudou":"http://www.tudou.com/albumplay/KE2zJh9AWII/2Otbf25W5h4.html?tpa=dW5pb25faWQ9MTAyMjEzXzEwMDAwMV8wMV8wMQ","youku":"http://v.youku.com/v_show/id_XODMzOTcyNjg0.html?tpa=dW5pb25faWQ9MTAyMjEzXzEwMDAwN18wMV8wMQ","fengxing":"http://www.fun.tv/vplay/m-115237.e-1?alliance=152055","qiyi":"http://www.iqiyi.com/v_19rrn94g50.html?vfm=f_191_360y","baofeng":"http://g.hd.baofeng.com/play/278/play-782278.html?fid=1324","pptv":"http://v.pptv.com/show/lyibYF3iclVZP2dHg.html"}
     * video_rec : [{"cover":"http://p4.qhimg.com/t01b2c8f106c3a06fc8.jpg","detail_url":"http://www.360kan.com/m/gKPkYUH6SHnAUR.html","title":"人再囧途之泰囧"},{"cover":"http://p9.qhimg.com/d/dy_cd647df6fd13a41877b4cf257946c8d9.jpg","detail_url":"http://www.360kan.com/m/gaPnZkYndXb2Th.html","title":"人在囧途"},{"cover":"http://p0.qhimg.com/d/dy_121e4ac14543bf9bccd234fe3c9f9699.jpg","detail_url":"http://www.360kan.com/m/gKbnYhH3RXfATB.html","title":"心花路放（2014）"},{"cover":"http://p1.qhimg.com/t0180f69b25dd6ee524.jpg","detail_url":"http://www.360kan.com/m/hqXiZBH1Q0P5UB.html","title":"后会无期"},{"cover":"http://p3.qhimg.com/d/dy_6be69b04c6a6b1b5c09347354fd15b8b.jpg","detail_url":"http://www.360kan.com/m/hKjqYkX8RHTASh.html","title":"人在囧途之冬囧"}]
     * act_s : [{"name":"黄渤","url":"http://baike.so.com/doc/4300540-4504310.html","image":"http://p5.qhmsg.com/dmsmty/120_110_100/t0175e2bf9bc9d6559e.jpg"},{"name":"徐峥","url":"http://baike.so.com/doc/1117785-1182683.html","image":"http://p3.qhmsg.com/dmsmty/120_110_100/t0185ffffc651394fac.jpg"},{"name":"袁泉","url":"http://baike.so.com/doc/2650955-2799320.html","image":"http://p3.qhmsg.com/dmsmty/120_110_100/t017f7ecd3855dce35d.jpg"},{"name":"周冬雨","url":"http://baike.so.com/doc/4084772-4283551.html","image":"http://p4.qhmsg.com/dmsmty/120_110_100/t01ab74a07f7a8f9512.jpg"}]
     */

    private String title;
    private String tag;
    private String act;
    private String year;
    private int rating;
    private String area;
    private String dir;
    private String desc;
    private String cover;
    private String vdo_status;
    private PlaylinksBean playlinks;
    private List<VideoRecBean> video_rec;
    private List<ActSBean> act_s;

    public SearchData(String title, String act, String cover) {
        this.title = title;
        this.act = act;
        this.cover = cover;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getAct() {
        return act;
    }

    public void setAct(String act) {
        this.act = act;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getVdo_status() {
        return vdo_status;
    }

    public void setVdo_status(String vdo_status) {
        this.vdo_status = vdo_status;
    }

    public PlaylinksBean getPlaylinks() {
        return playlinks;
    }

    public void setPlaylinks(PlaylinksBean playlinks) {
        this.playlinks = playlinks;
    }

    public List<VideoRecBean> getVideo_rec() {
        return video_rec;
    }

    public void setVideo_rec(List<VideoRecBean> video_rec) {
        this.video_rec = video_rec;
    }

    public List<ActSBean> getAct_s() {
        return act_s;
    }

    public void setAct_s(List<ActSBean> act_s) {
        this.act_s = act_s;
    }

    public static class PlaylinksBean {
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

    public static class VideoRecBean {
        /**
         * cover : http://p4.qhimg.com/t01b2c8f106c3a06fc8.jpg
         * detail_url : http://www.360kan.com/m/gKPkYUH6SHnAUR.html
         * title : 人再囧途之泰囧
         */

        private String cover;
        private String detail_url;
        private String title;

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getDetail_url() {
            return detail_url;
        }

        public void setDetail_url(String detail_url) {
            this.detail_url = detail_url;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public static class ActSBean {
        /**
         * name : 黄渤
         * url : http://baike.so.com/doc/4300540-4504310.html
         * image : http://p5.qhmsg.com/dmsmty/120_110_100/t0175e2bf9bc9d6559e.jpg
         */

        private String name;
        private String url;
        private String image;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }
}
