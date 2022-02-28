package yixiang;

import com.aliyun.vod.upload.impl.PutObjectProgressListener;
import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * 使用上传SDK进行视频文件上传
 */
public class Upload {
    /**
     * 流式上传接口
     *
     * @param accessKeyId
     * @param accessKeySecret
     * @param title
     * @param fileName
     * @param inputStream
     */
    private static void testUploadStream(String accessKeyId, String accessKeySecret, String title, String fileName, InputStream inputStream) {
        UploadStreamRequest request = new UploadStreamRequest(accessKeyId, accessKeySecret, title, fileName, inputStream);

        /* 自定义消息回调设置，参数说明请参见基本数据类型 */
        //request.setUserData(""{\"Extend\":{\"test\":\"www\",\"localId\":\"xxxx\"},\"MessageCallback\":{\"CallbackURL\":\"http://test.test.com\"}}"");
        /* 视频分类ID(可选) */
        //request.setCateId(0);
        request.setCateId(7325L);
        /* 视频标签,多个用逗号分隔(可选) */
        //request.setTags("标签1,标签2");
        /* 视频描述(可选) */
        //request.setDescription("视频描述");
        /* 封面图片(可选) ，如http://****.example.com/image_01.jpg*/
        //request.setCoverURL("<Your CoverURL>");
        /* 模板组ID(可选) */
        //request.setTemplateGroupId("8c4792cbc8694e****fd5330e56a33d");
        /* 工作流ID(可选) */
        //request.setWorkflowId("d4430d07361f****1339577859b0177b");


        /* 开启默认上传进度回调 */
        request.setPrintProgress(true);
        /* 设置自定义上传进度回调 (必须继承 VoDProgressListener) */
        request.setProgressListener(new PutObjectProgressListener());
//        request.setProgressListener(new MyVoDProgressListener(inputStream.available(),key));




        /* 存储区域(可选) */
        //request.setStorageLocation("outin-20170323****266-5sejdln9o.oss-cn-shanghai.aliyuncs.com");
        request.setStorageLocation("outin-644b9f2c692011ecba2b00163e038793.oss-cn-beijing.aliyuncs.com");
        /* 点播服务接入点 */
//        request.setApiRegionId("cn-shanghai");
        /* ECS部署区域*/
        // request.setEcsRegionId("cn-shanghai");
        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadStreamResponse response = uploader.uploadStream(request);
        System.out.print("RequestId=" + response.getRequestId() + "\n");  //请求视频点播服务的请求ID
        if (response.isSuccess()) {
            System.out.print("VideoId=" + response.getVideoId() + "\n");
        } else { //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
            System.out.print("VideoId=" + response.getVideoId() + "\n");
            System.out.print("ErrorCode=" + response.getCode() + "\n");
            System.out.print("ErrorMessage=" + response.getMessage() + "\n");
        }
    }

    public static void main(String[] args) {
        /**
         * 用户可自行添加url数据源,并传入视频媒资信息，上传视频资源
         */
        InputStream inputStream = null;
        //您的视频地址。如http://example.aliyundoc.com/video/****.mp4
//        String url = "<Your File URL>";
        String url = "https://vdo.lynkage.cn/sv/562a82ca-17e0920809c/562a82ca-17e0920809c.mp4";
        try {
            inputStream = new URL(url).openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //以下参数重的AccessKey ID, AccessKey Secret提前准备好的AccessKey信息。<Your Video Title>为视频标题。<Your Video with File Extension>为含文件扩展名的视频，如video-1.mp4。
        testUploadStream("LTAI5tScLGi4xKmGRfdmxpPg", "nUXGv30JoJpIgtGffSHIJxfZbtUl97",
                "童曰让我自信说", "562a82ca-17e0920809c.mp4", inputStream);
//        testUploadStream("<AccessKey Id>", "<AccessKey Secret>", "<Your Video Title>", "<Your Video with File Extension>", inputStream);
    }
}