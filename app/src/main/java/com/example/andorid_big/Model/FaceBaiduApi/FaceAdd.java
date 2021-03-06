package com.example.andorid_big.Model.FaceBaiduApi;

import android.util.Base64;

import com.baidu.ai.aip.utils.Base64Util;
import com.baidu.ai.aip.utils.GsonUtils;
import com.baidu.ai.aip.utils.HttpUtil;

import java.util.HashMap;
import java.util.Map;

import static com.example.andorid_big.Model.FaceBaiduApi.AuthService.getAuth;

public class FaceAdd {

    /**
     * 重要提示代码中所需工具类
     * FileUtil,Base64Util,HttpUtil,GsonUtils请从
     * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
     * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
     * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
     * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
     * 下载
     */
    public static String add(String user1, String image, String AccessToken) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/add";
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("image", image);//027d8308a2ec665acb1bdf63e513bcb9
            map.put("group_id", "group_repeat");//示例中不进行分组
            map.put("user_id", user1);
            map.put("user_info", "abc");
            map.put("liveness_control", "NONE"); //不进行活体检测
            map.put("image_type", "BASE64");//FACE_TOKEN
            map.put("quality_control", "NORMAL");

            String param = GsonUtils.toJson(map);

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = AccessToken;

            String result = HttpUtil.post(url, accessToken, "application/json", param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}

