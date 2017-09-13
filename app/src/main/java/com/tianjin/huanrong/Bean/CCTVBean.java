package com.tianjin.huanrong.Bean;

import java.util.List;

/**
 * Created by Mr.zhang on 2017/6/29.
 */

public class CCTVBean {
    /**
     * totalCount : 10
     * message : 200
     * success : 0
     * data : [{"VideoId":344,"VideoName":"【20170602】首旭视点","VideoType":"鲸鱼APP视频","TypeId":1,"VIntroduction":"陈晓龙","TypeIntroduction":" ","CreateTime":"2017-06-05 10:28:18","VideoUrl":"http://www.006006.cn/shipin/0602.mp4","VideoPicture":"/UploadFiles/Images/20170605101828_433.png","Total":0,"UpdateTime":"/Date(-62135596800000)/","ShootingDate":"2017-06-02 00:00:00","RoleName":null,"StateName":null,"AdminName":null,"InfoTypeName":null,"CreateName":null,"UpdateName":null,"ModuleName":null},{"VideoId":342,"VideoName":"【20170519】首旭视点","VideoType":"鲸鱼APP视频","TypeId":1,"VIntroduction":"王子航","TypeIntroduction":" ","CreateTime":"2017-05-22 11:09:55","VideoUrl":"http://www.006006.cn/shipin/0519.mp4","VideoPicture":"/UploadFiles/Images/20170522115509_352.png","Total":0,"UpdateTime":"/Date(-62135596800000)/","ShootingDate":"2017-05-19 00:00:00","RoleName":null,"StateName":null,"AdminName":null,"InfoTypeName":null,"CreateName":null,"UpdateName":null,"ModuleName":null},{"VideoId":323,"VideoName":"【20170505】首旭视点","VideoType":"鲸鱼APP视频","TypeId":1,"VIntroduction":"王子航","TypeIntroduction":" ","CreateTime":"2017-05-08 09:52:09","VideoUrl":"http://www.006006.cn/shipin/0505.mp4","VideoPicture":"/UploadFiles/Images/20170508090952_713.png","Total":0,"UpdateTime":"/Date(-62135596800000)/","ShootingDate":"2017-05-05 00:00:00","RoleName":null,"StateName":null,"AdminName":null,"InfoTypeName":null,"CreateName":null,"UpdateName":null,"ModuleName":null},{"VideoId":278,"VideoName":"【20170428】首旭视点","VideoType":"鲸鱼APP视频","TypeId":1,"VIntroduction":"戚鹏","TypeIntroduction":" ","CreateTime":"2017-04-26 15:51:17","VideoUrl":"http://www.006006.cn/shipin/0428.mp4","VideoPicture":"/UploadFiles/Images/20170426151751_400.png","Total":0,"UpdateTime":"/Date(-62135596800000)/","ShootingDate":"2017-04-28 00:00:00","RoleName":null,"StateName":null,"AdminName":null,"InfoTypeName":null,"CreateName":null,"UpdateName":null,"ModuleName":null},{"VideoId":318,"VideoName":"【20170428】首旭视点","VideoType":"鲸鱼APP视频","TypeId":1,"VIntroduction":"戚鹏","TypeIntroduction":" ","CreateTime":"2017-05-04 09:27:00","VideoUrl":"http://www.006006.cn/shipin/0428.mp4","VideoPicture":"/UploadFiles/Images/20170504090027_857.png","Total":0,"UpdateTime":"/Date(-62135596800000)/","ShootingDate":"2017-04-28 00:00:00","RoleName":null,"StateName":null,"AdminName":null,"InfoTypeName":null,"CreateName":null,"UpdateName":null,"ModuleName":null},{"VideoId":308,"VideoName":"【20170421】首旭视点","VideoType":"鲸鱼APP视频","TypeId":1,"VIntroduction":"戚鹏","TypeIntroduction":" ","CreateTime":"2017-05-02 16:04:14","VideoUrl":"http://www.006006.cn/shipin/0421.mp4","VideoPicture":"/UploadFiles/Images/20170502161404_267.png","Total":0,"UpdateTime":"/Date(-62135596800000)/","ShootingDate":"2017-04-21 00:00:00","RoleName":null,"StateName":null,"AdminName":null,"InfoTypeName":null,"CreateName":null,"UpdateName":null,"ModuleName":null},{"VideoId":277,"VideoName":"【20170414】首旭视点","VideoType":"鲸鱼APP视频","TypeId":1,"VIntroduction":"戚鹏","TypeIntroduction":" ","CreateTime":"2017-04-26 15:48:49","VideoUrl":"http://www.006006.cn/shipin/0414.mp4","VideoPicture":"/UploadFiles/Images/20170426154948_459.png","Total":0,"UpdateTime":"/Date(-62135596800000)/","ShootingDate":"2017-04-14 00:00:00","RoleName":null,"StateName":null,"AdminName":null,"InfoTypeName":null,"CreateName":null,"UpdateName":null,"ModuleName":null},{"VideoId":260,"VideoName":"【20170407】首旭视点","VideoType":"鲸鱼APP视频","TypeId":1,"VIntroduction":"戚鹏","TypeIntroduction":" ","CreateTime":"2017-04-11 14:23:42","VideoUrl":"http://www.006006.cn/shipin/0407.mp4","VideoPicture":"/UploadFiles/Images/20170411144223_391.png","Total":0,"UpdateTime":"/Date(-62135596800000)/","ShootingDate":"2017-04-07 00:00:00","RoleName":null,"StateName":null,"AdminName":null,"InfoTypeName":null,"CreateName":null,"UpdateName":null,"ModuleName":null},{"VideoId":256,"VideoName":"【20170331】首旭视点","VideoType":"鲸鱼APP视频","TypeId":1,"VIntroduction":"陈晓龙","TypeIntroduction":" ","CreateTime":"2017-04-04 14:36:56","VideoUrl":"http://www.006006.cn/shipin/0331.mp4","VideoPicture":"/UploadFiles/Images/20170404145636_858.png","Total":0,"UpdateTime":"/Date(-62135596800000)/","ShootingDate":"2017-03-31 00:00:00","RoleName":null,"StateName":null,"AdminName":null,"InfoTypeName":null,"CreateName":null,"UpdateName":null,"ModuleName":null},{"VideoId":231,"VideoName":"【20170324】首旭视点","VideoType":"鲸鱼APP视频","TypeId":1,"VIntroduction":"陈晓龙","TypeIntroduction":" ","CreateTime":"2017-03-27 13:41:17","VideoUrl":"http://www.006006.cn/shipin/0324.mp4","VideoPicture":"/UploadFiles/Images/20170327131741_924.png","Total":0,"UpdateTime":"/Date(-62135596800000)/","ShootingDate":"2017-03-24 00:00:00","RoleName":null,"StateName":null,"AdminName":null,"InfoTypeName":null,"CreateName":null,"UpdateName":null,"ModuleName":null}]
     */

    private String totalCount;
    private String message;
    private String success;
    private List<DataBean> data;

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * VideoId : 344
         * VideoName : 【20170602】首旭视点
         * VideoType : 鲸鱼APP视频
         * TypeId : 1
         * VIntroduction : 陈晓龙
         * TypeIntroduction :
         * CreateTime : 2017-06-05 10:28:18
         * VideoUrl : http://www.006006.cn/shipin/0602.mp4
         * VideoPicture : /UploadFiles/Images/20170605101828_433.png
         * Total : 0
         * UpdateTime : /Date(-62135596800000)/
         * ShootingDate : 2017-06-02 00:00:00
         * RoleName : null
         * StateName : null
         * AdminName : null
         * InfoTypeName : null
         * CreateName : null
         * UpdateName : null
         * ModuleName : null
         */

        private int VideoId;
        private String VideoName;
        private String VideoType;
        private int TypeId;
        private String VIntroduction;
        private String TypeIntroduction;
        private String CreateTime;
        private String VideoUrl;
        private String VideoPicture;
        private int Total;
        private String UpdateTime;
        private String ShootingDate;
        private Object RoleName;
        private Object StateName;
        private Object AdminName;
        private Object InfoTypeName;
        private Object CreateName;
        private Object UpdateName;
        private Object ModuleName;

        public int getVideoId() {
            return VideoId;
        }

        public void setVideoId(int VideoId) {
            this.VideoId = VideoId;
        }

        public String getVideoName() {
            return VideoName;
        }

        public void setVideoName(String VideoName) {
            this.VideoName = VideoName;
        }

        public String getVideoType() {
            return VideoType;
        }

        public void setVideoType(String VideoType) {
            this.VideoType = VideoType;
        }

        public int getTypeId() {
            return TypeId;
        }

        public void setTypeId(int TypeId) {
            this.TypeId = TypeId;
        }

        public String getVIntroduction() {
            return VIntroduction;
        }

        public void setVIntroduction(String VIntroduction) {
            this.VIntroduction = VIntroduction;
        }

        public String getTypeIntroduction() {
            return TypeIntroduction;
        }

        public void setTypeIntroduction(String TypeIntroduction) {
            this.TypeIntroduction = TypeIntroduction;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public String getVideoUrl() {
            return VideoUrl;
        }

        public void setVideoUrl(String VideoUrl) {
            this.VideoUrl = VideoUrl;
        }

        public String getVideoPicture() {
            return VideoPicture;
        }

        public void setVideoPicture(String VideoPicture) {
            this.VideoPicture = VideoPicture;
        }

        public int getTotal() {
            return Total;
        }

        public void setTotal(int Total) {
            this.Total = Total;
        }

        public String getUpdateTime() {
            return UpdateTime;
        }

        public void setUpdateTime(String UpdateTime) {
            this.UpdateTime = UpdateTime;
        }

        public String getShootingDate() {
            return ShootingDate;
        }

        public void setShootingDate(String ShootingDate) {
            this.ShootingDate = ShootingDate;
        }

        public Object getRoleName() {
            return RoleName;
        }

        public void setRoleName(Object RoleName) {
            this.RoleName = RoleName;
        }

        public Object getStateName() {
            return StateName;
        }

        public void setStateName(Object StateName) {
            this.StateName = StateName;
        }

        public Object getAdminName() {
            return AdminName;
        }

        public void setAdminName(Object AdminName) {
            this.AdminName = AdminName;
        }

        public Object getInfoTypeName() {
            return InfoTypeName;
        }

        public void setInfoTypeName(Object InfoTypeName) {
            this.InfoTypeName = InfoTypeName;
        }

        public Object getCreateName() {
            return CreateName;
        }

        public void setCreateName(Object CreateName) {
            this.CreateName = CreateName;
        }

        public Object getUpdateName() {
            return UpdateName;
        }

        public void setUpdateName(Object UpdateName) {
            this.UpdateName = UpdateName;
        }

        public Object getModuleName() {
            return ModuleName;
        }

        public void setModuleName(Object ModuleName) {
            this.ModuleName = ModuleName;
        }
    }
}
