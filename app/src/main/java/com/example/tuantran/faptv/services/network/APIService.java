package com.example.tuantran.faptv.services.network;

import com.example.tuantran.faptv.utils.Contanst;

public class APIService {
        // ket hop
        public static Dataservice getService(){
            // Khoi tao nhung phuong thuc trong dataservice
            return  APIRetrofitClient.getClient(Contanst.URL_YOUTUBE_API_V3).create(Dataservice.class);
        }

}
