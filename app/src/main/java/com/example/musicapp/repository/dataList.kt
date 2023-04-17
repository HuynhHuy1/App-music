package com.example.musicapp.repository

import com.example.musicapp.R
import com.example.musicapp.model.Model

class dataList  {
    fun loadData() : ArrayList<Model>{
        val single = " Noo Phước Thịnh"
        return arrayListOf(
            Model(R.drawable.denvoinhau, " Đến với nhau là sai ",single, R.raw.denvoinhau),
            Model(R.drawable.causei, " Cause I Love You ",single,R.raw.caui),
            Model(R.drawable.ckta, " Chạm khẽ tim anh một chút thôi ",single,R.raw.chamkhetimanh),
            Model(R.drawable.dinhmenh, " Định mệnh ",single,R.raw.dinhmenh),
            Model(R.drawable.emdathuong, " Em đã thương người ta hơn anh ",single,R.raw.emdathuong),
            Model(R.drawable.idont, " I don't belliving you ",single,R.raw.idont),
            Model(R.drawable.matem2, " Mất em ",single,R.raw.matem),
            Model(R.drawable.thuongmaycungla, " Thương mấy cũng thành ngừoi dưng ",single,R.raw.thuongmay),
            Model(R.drawable.yeumotnguoi, " Yêu một ngừoi sao buồn đến thế ",single,R.raw.yeumotnguoi),
            Model(R.drawable.thuongemladie, " Thương em là điều anh không thể ngờ ",single,R.raw.thuongem),
        )
    }
}