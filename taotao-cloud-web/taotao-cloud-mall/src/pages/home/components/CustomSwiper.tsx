import {Image, Swiper, SwiperItem} from "@tarojs/components";
import React from "react";
import {Banner} from "@/pages/home";

interface IProps {
  statusBarHeight?: number;
  bannerDataList?: Banner[]
}

const CustomSwiper: Taro.FC<IProps> = (props) => {
  return (
    <Swiper
      indicatorColor="#333"
      indicatorActiveColor="#00bc71"
      circular
      indicatorDots
      autoplay
      style={
        props.statusBarHeight <= 20 ?
          "width: 100%;height: 250PX;z-index: 1;"
          : `width: 100%;height: ${props.statusBarHeight - 20 + 250}PX;z-index: 1;`
      }
      className="topBanner-box"
    >
      {props.bannerDataList && props.bannerDataList.map(topBanner => (
        topBanner &&
        <SwiperItem key={topBanner.id}>
          <Image
            src={topBanner.imageUrl}
            style={
              props.statusBarHeight <= 20
                ? 'width:100%;height:250PX;z-index:0;'
                : `width:100%;height:${props.statusBarHeight - 20 + 250}PX;z-index:0;`
            }
          />
        </SwiperItem>
      ))}
    </Swiper>
  )
}

export default CustomSwiper
