package com.example.app.Constants;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class Constant {
    public  final  static Map<String,Double> PROMOTION_RATE_P1=new HashMap();
    static {
        //60 sec  3 bath
        PROMOTION_RATE_P1.put("FIRST_TIME",new Double(3));
        //>60 sec  1 bath per minute
        PROMOTION_RATE_P1.put("SECOND_TIME",new Double(1));
    };
    public static final ObjectMapper G_OBJ_MAPPER = new ObjectMapper();

    static {
        G_OBJ_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
    public  enum PROMOTION{
        P1(PROMOTION_RATE_P1);
        Map promotion;
        PROMOTION(Map promotion){
            this.promotion=promotion;
        }

        public Map getPromotion() {
            return promotion;
        }
    }
    public  enum TEL_USAGE_TEXT_FORMAT{
        DATE(0),
        STIME(1),
        ETIME(2),
        TEL_NO(3),
        PROMOTION_NAME(4);
        int idx;
        TEL_USAGE_TEXT_FORMAT(int idx){
            this.idx=idx;
        }

        public int getIdx() {
            return idx;
        }
    }

}
