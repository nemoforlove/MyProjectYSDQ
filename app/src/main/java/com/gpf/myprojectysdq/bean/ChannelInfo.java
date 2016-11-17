package com.gpf.myprojectysdq.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/10.
 */

public class ChannelInfo {

    /**
     * status : 200
     * notice : success cached
     * info : {"channel":[{"order_num":"1","name":"电影","channel":"dy","search":"","show_version":"","close_version":"","icon":"http://imgwx4.2345.com/dianyingimg/mversion/img/app_channel_appv43/2ebfa6377b3b1ba2f16df430944cba61.jpg","py":"dy"},{"order_num":"2","name":"电视剧","channel":"tv","search":"","show_version":"","close_version":"","icon":"http://imgwx1.2345.com/dianyingimg/mversion/img/app_channel_appv43/3ddf5425b0aedcaf0d236d2d806ce9a5.jpg","py":"tv"},{"order_num":"3","name":"动漫","channel":"dm","search":"","show_version":"","close_version":"","icon":"http://imgwx4.2345.com/dianyingimg/mversion/img/app_channel_appv43/a3eddc8268385eb98335fe24c0511f23.jpg","py":"dm"},{"order_num":"4","name":"综艺","channel":"zy","search":"","show_version":"","close_version":"","icon":"http://imgwx3.2345.com/dianyingimg/mversion/img/app_channel_appv43/743d2f2d13974fc794bec9b37c222d51.jpg","py":"zy"}],"custom":[{"order_num":"5","name":"今日头条","channel":"shortvideo","search":"toutiao","show_version":"","close_version":"","icon":"http://imgwx2.2345.com/dianyingimg/mversion/img/app_channel_appv43/40d38bf0bc394e849f351f3abaa7049e.png","py":"toutiao"},{"order_num":"6","name":"娱乐八卦","channel":"shortvideo","search":"yule","show_version":"","close_version":"","icon":"http://imgwx3.2345.com/dianyingimg/mversion/img/app_channel_appv43/01fe3b8b17c200ca29d3072701d0f294.png","py":"yule"},{"order_num":"7","name":"付费专区","channel":"custom","search":"293","show_version":"","close_version":"","icon":"http://imgwx2.2345.com/dianyingimg/mversion/img/app_channel_appv43/a6b9a4c433372a1eac42b3fc3d0fa499.png","py":"zhuanqu"},{"order_num":"9","name":"爆笑秀场","channel":"shortvideo","search":"gaoxiao","show_version":"","close_version":"","icon":"http://imgwx2.2345.com/dianyingimg/mversion/img/app_channel_appv43/626e36d233832ebd3cd763561e69ae37.png","py":"gaoxiao"},{"order_num":"12","name":"特色专题","channel":"customList","search":"","show_version":"","close_version":"","icon":"http://imgwx2.2345.com/dianyingimg/mversion/img/app_channel_appv43/481e7af86bd403c61166e4042110f267.png","py":"ztlb"},{"order_num":"13","name":"百视通","channel":"link","search":"http://api.v.2345.com/html/bestv/bestv.html","show_version":"","close_version":"","icon":"http://imgwx3.2345.com/dianyingimg/mversion/img/app_channel_appv43/f2991f7795ac3dcb923189741c572e3a.png","py":"bestv"},{"order_num":"14","name":"热播榜","channel":"bd","search":"","show_version":"","close_version":"","icon":"http://imgwx4.2345.com/dianyingimg/mversion/img/app_channel_appv43/2335c80f04829a46c74262d1a2d3a974.png","py":"bd"},{"order_num":"17","name":"游戏中心","channel":"game","search":"游戏","show_version":"","close_version":"","icon":"http://imgwx4.2345.com/dianyingimg/mversion/img/app_channel_appv43/d579521e8300a152e14c394c1ffecfa6.png","py":"game"},{"order_num":"19","name":"热血体育","channel":"shortvideo","search":"tiyu","show_version":"","close_version":"","icon":"http://imgwx4.2345.com/dianyingimg/mversion/img/app_channel_appv43/6c5b6a74256ed6ad4c61b769ebf5aab9.png","py":"tiyu"},{"order_num":"19","name":"游戏视频","channel":"shortvideo","search":"youxi","show_version":"","close_version":"","icon":"http://imgwx1.2345.com/dianyingimg/mversion/img/app_channel_appv43/395e3475bf780417428088d7c6761f6e.png","py":"youxi"},{"order_num":"20","name":"舞蹈精选","channel":"shortvideo","search":"wudao","show_version":"","close_version":"","icon":"http://imgwx4.2345.com/dianyingimg/mversion/img/app_channel_appv43/0abe7d633e5902bf57467305d2171e47.png","py":"wudao"},{"order_num":"21","name":"下载专区","channel":"dlRecommend","search":"","show_version":"","close_version":"","icon":"http://imgwx3.2345.com/dianyingimg/mversion/img/app_channel_appv43/b7dd53d8aca6f4113e7362d9eb7a8d8c.png","py":"xzzq"}],"propose":[{"order_num":"31","name":"浪漫爱情","channel":"dy","search":"dy_aiqing","show_version":"","close_version":"","icon":"","py":"aqli"},{"order_num":"32","name":"炫酷特工","channel":"dy","search":"dy_dongzuo","show_version":"","close_version":"","icon":"","py":"xktg"},{"order_num":"33","name":"经典老剧","channel":"tv","search":"tv_jingdian","show_version":"","close_version":"","icon":"","py":"jdjc"},{"order_num":"36","name":"动画电影","channel":"dm","search":"dm_dhddy","show_version":"","close_version":"","icon":"","py":"dhdy"},{"order_num":"37","name":"好莱坞大片","channel":"dy","search":"dy_haolaiwu","show_version":"","close_version":"","icon":"","py":"haolaiwu"},{"order_num":"39","name":"整蛊喜剧","channel":"dy","search":"dy_xiju","show_version":"","close_version":"","icon":"","py":"xiju"}],"caeFlag":""}
     */

    private String status;
    private String notice;
    private InfoBean info;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public static class InfoBean {
        /**
         * channel : [{"order_num":"1","name":"电影","channel":"dy","search":"","show_version":"","close_version":"","icon":"http://imgwx4.2345.com/dianyingimg/mversion/img/app_channel_appv43/2ebfa6377b3b1ba2f16df430944cba61.jpg","py":"dy"},{"order_num":"2","name":"电视剧","channel":"tv","search":"","show_version":"","close_version":"","icon":"http://imgwx1.2345.com/dianyingimg/mversion/img/app_channel_appv43/3ddf5425b0aedcaf0d236d2d806ce9a5.jpg","py":"tv"},{"order_num":"3","name":"动漫","channel":"dm","search":"","show_version":"","close_version":"","icon":"http://imgwx4.2345.com/dianyingimg/mversion/img/app_channel_appv43/a3eddc8268385eb98335fe24c0511f23.jpg","py":"dm"},{"order_num":"4","name":"综艺","channel":"zy","search":"","show_version":"","close_version":"","icon":"http://imgwx3.2345.com/dianyingimg/mversion/img/app_channel_appv43/743d2f2d13974fc794bec9b37c222d51.jpg","py":"zy"}]
         * custom : [{"order_num":"5","name":"今日头条","channel":"shortvideo","search":"toutiao","show_version":"","close_version":"","icon":"http://imgwx2.2345.com/dianyingimg/mversion/img/app_channel_appv43/40d38bf0bc394e849f351f3abaa7049e.png","py":"toutiao"},{"order_num":"6","name":"娱乐八卦","channel":"shortvideo","search":"yule","show_version":"","close_version":"","icon":"http://imgwx3.2345.com/dianyingimg/mversion/img/app_channel_appv43/01fe3b8b17c200ca29d3072701d0f294.png","py":"yule"},{"order_num":"7","name":"付费专区","channel":"custom","search":"293","show_version":"","close_version":"","icon":"http://imgwx2.2345.com/dianyingimg/mversion/img/app_channel_appv43/a6b9a4c433372a1eac42b3fc3d0fa499.png","py":"zhuanqu"},{"order_num":"9","name":"爆笑秀场","channel":"shortvideo","search":"gaoxiao","show_version":"","close_version":"","icon":"http://imgwx2.2345.com/dianyingimg/mversion/img/app_channel_appv43/626e36d233832ebd3cd763561e69ae37.png","py":"gaoxiao"},{"order_num":"12","name":"特色专题","channel":"customList","search":"","show_version":"","close_version":"","icon":"http://imgwx2.2345.com/dianyingimg/mversion/img/app_channel_appv43/481e7af86bd403c61166e4042110f267.png","py":"ztlb"},{"order_num":"13","name":"百视通","channel":"link","search":"http://api.v.2345.com/html/bestv/bestv.html","show_version":"","close_version":"","icon":"http://imgwx3.2345.com/dianyingimg/mversion/img/app_channel_appv43/f2991f7795ac3dcb923189741c572e3a.png","py":"bestv"},{"order_num":"14","name":"热播榜","channel":"bd","search":"","show_version":"","close_version":"","icon":"http://imgwx4.2345.com/dianyingimg/mversion/img/app_channel_appv43/2335c80f04829a46c74262d1a2d3a974.png","py":"bd"},{"order_num":"17","name":"游戏中心","channel":"game","search":"游戏","show_version":"","close_version":"","icon":"http://imgwx4.2345.com/dianyingimg/mversion/img/app_channel_appv43/d579521e8300a152e14c394c1ffecfa6.png","py":"game"},{"order_num":"19","name":"热血体育","channel":"shortvideo","search":"tiyu","show_version":"","close_version":"","icon":"http://imgwx4.2345.com/dianyingimg/mversion/img/app_channel_appv43/6c5b6a74256ed6ad4c61b769ebf5aab9.png","py":"tiyu"},{"order_num":"19","name":"游戏视频","channel":"shortvideo","search":"youxi","show_version":"","close_version":"","icon":"http://imgwx1.2345.com/dianyingimg/mversion/img/app_channel_appv43/395e3475bf780417428088d7c6761f6e.png","py":"youxi"},{"order_num":"20","name":"舞蹈精选","channel":"shortvideo","search":"wudao","show_version":"","close_version":"","icon":"http://imgwx4.2345.com/dianyingimg/mversion/img/app_channel_appv43/0abe7d633e5902bf57467305d2171e47.png","py":"wudao"},{"order_num":"21","name":"下载专区","channel":"dlRecommend","search":"","show_version":"","close_version":"","icon":"http://imgwx3.2345.com/dianyingimg/mversion/img/app_channel_appv43/b7dd53d8aca6f4113e7362d9eb7a8d8c.png","py":"xzzq"}]
         * propose : [{"order_num":"31","name":"浪漫爱情","channel":"dy","search":"dy_aiqing","show_version":"","close_version":"","icon":"","py":"aqli"},{"order_num":"32","name":"炫酷特工","channel":"dy","search":"dy_dongzuo","show_version":"","close_version":"","icon":"","py":"xktg"},{"order_num":"33","name":"经典老剧","channel":"tv","search":"tv_jingdian","show_version":"","close_version":"","icon":"","py":"jdjc"},{"order_num":"36","name":"动画电影","channel":"dm","search":"dm_dhddy","show_version":"","close_version":"","icon":"","py":"dhdy"},{"order_num":"37","name":"好莱坞大片","channel":"dy","search":"dy_haolaiwu","show_version":"","close_version":"","icon":"","py":"haolaiwu"},{"order_num":"39","name":"整蛊喜剧","channel":"dy","search":"dy_xiju","show_version":"","close_version":"","icon":"","py":"xiju"}]
         * caeFlag :
         */

        private String caeFlag;
        private List<ChannelBean> channel;
        private List<CustomBean> custom;
        private List<ProposeBean> propose;

        public String getCaeFlag() {
            return caeFlag;
        }

        public void setCaeFlag(String caeFlag) {
            this.caeFlag = caeFlag;
        }

        public List<ChannelBean> getChannel() {
            return channel;
        }

        public void setChannel(List<ChannelBean> channel) {
            this.channel = channel;
        }

        public List<CustomBean> getCustom() {
            return custom;
        }

        public void setCustom(List<CustomBean> custom) {
            this.custom = custom;
        }

        public List<ProposeBean> getPropose() {
            return propose;
        }

        public void setPropose(List<ProposeBean> propose) {
            this.propose = propose;
        }

        public static class ChannelBean {
            /**
             * order_num : 1
             * name : 电影
             * channel : dy
             * search :
             * show_version :
             * close_version :
             * icon : http://imgwx4.2345.com/dianyingimg/mversion/img/app_channel_appv43/2ebfa6377b3b1ba2f16df430944cba61.jpg
             * py : dy
             */

            private String order_num;
            private String name;
            private String channel;
            private String search;
            private String show_version;
            private String close_version;
            private String icon;
            private String py;

            public String getOrder_num() {
                return order_num;
            }

            public void setOrder_num(String order_num) {
                this.order_num = order_num;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getChannel() {
                return channel;
            }

            public void setChannel(String channel) {
                this.channel = channel;
            }

            public String getSearch() {
                return search;
            }

            public void setSearch(String search) {
                this.search = search;
            }

            public String getShow_version() {
                return show_version;
            }

            public void setShow_version(String show_version) {
                this.show_version = show_version;
            }

            public String getClose_version() {
                return close_version;
            }

            public void setClose_version(String close_version) {
                this.close_version = close_version;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getPy() {
                return py;
            }

            public void setPy(String py) {
                this.py = py;
            }
        }

        public static class CustomBean {
            /**
             * order_num : 5
             * name : 今日头条
             * channel : shortvideo
             * search : toutiao
             * show_version :
             * close_version :
             * icon : http://imgwx2.2345.com/dianyingimg/mversion/img/app_channel_appv43/40d38bf0bc394e849f351f3abaa7049e.png
             * py : toutiao
             */

            private String order_num;
            private String name;
            private String channel;
            private String search;
            private String show_version;
            private String close_version;
            private String icon;
            private String py;

            public String getOrder_num() {
                return order_num;
            }

            public void setOrder_num(String order_num) {
                this.order_num = order_num;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getChannel() {
                return channel;
            }

            public void setChannel(String channel) {
                this.channel = channel;
            }

            public String getSearch() {
                return search;
            }

            public void setSearch(String search) {
                this.search = search;
            }

            public String getShow_version() {
                return show_version;
            }

            public void setShow_version(String show_version) {
                this.show_version = show_version;
            }

            public String getClose_version() {
                return close_version;
            }

            public void setClose_version(String close_version) {
                this.close_version = close_version;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getPy() {
                return py;
            }

            public void setPy(String py) {
                this.py = py;
            }
        }

        public static class ProposeBean {
            /**
             * order_num : 31
             * name : 浪漫爱情
             * channel : dy
             * search : dy_aiqing
             * show_version :
             * close_version :
             * icon :
             * py : aqli
             */

            private String order_num;
            private String name;
            private String channel;
            private String search;
            private String show_version;
            private String close_version;
            private String icon;
            private String py;

            public String getOrder_num() {
                return order_num;
            }

            public void setOrder_num(String order_num) {
                this.order_num = order_num;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getChannel() {
                return channel;
            }

            public void setChannel(String channel) {
                this.channel = channel;
            }

            public String getSearch() {
                return search;
            }

            public void setSearch(String search) {
                this.search = search;
            }

            public String getShow_version() {
                return show_version;
            }

            public void setShow_version(String show_version) {
                this.show_version = show_version;
            }

            public String getClose_version() {
                return close_version;
            }

            public void setClose_version(String close_version) {
                this.close_version = close_version;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getPy() {
                return py;
            }

            public void setPy(String py) {
                this.py = py;
            }
        }
    }
}
