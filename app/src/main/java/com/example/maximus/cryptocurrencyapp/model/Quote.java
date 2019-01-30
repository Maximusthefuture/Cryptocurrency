package com.example.maximus.cryptocurrencyapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Quote {
        @SerializedName("USD")
        @Expose
        private USD usd;

        public USD getUSD() {
            return usd;
        }

        public void setUSD(USD usd) {
            this.usd = usd;
        }


}
