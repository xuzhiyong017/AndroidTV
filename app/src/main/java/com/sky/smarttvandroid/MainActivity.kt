package com.sky.smarttvandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shuyu.gsyvideoplayer.utils.OrientationUtils
import com.sky.smarttvandroid.model.LiveModel
import com.sky.smarttvandroid.widget.CenterLayoutManager
import com.sky.smarttvandroid.widget.CustomRecyclerView
import com.zhy.adapter.recyclerview.CommonAdapter
import com.zhy.adapter.recyclerview.base.ViewHolder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : FragmentActivity() {

    private lateinit var orientationUtils: OrientationUtils
    private  var LiveModels = arrayListOf<LiveModel>()
    private  lateinit var centerLayoutManager:CenterLayoutManager
    private lateinit var adapter: CommonAdapter<LiveModel>
    val Default_POSITION = 0;
    private var curPosition = Default_POSITION //当前播放位置

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        initheaders()
        playDefault()
    }

    private fun playDefault() {
        switchPlay()
    }

    private fun initheaders() {
        loadVideoData()
        centerLayoutManager = CenterLayoutManager(this)

        recycleView.itemAnimator = null
        recycleView.layoutManager = centerLayoutManager
        adapter=   object : CommonAdapter<LiveModel>(this,R.layout.live_item_view, LiveModels){
            override fun convert(holder: ViewHolder, model: LiveModel, position: Int) {
                holder.setText(R.id.tv_title,model.title)
                holder.itemView.setOnClickListener {
                    curPosition = position
                    holder.itemView.isSelected = true
//                    centerLayoutManager.smoothScrollToPosition(recycleView,
//                        RecyclerView.State(),position)
                    switchPlay()
                }
                holder.itemView.isFocusable = true

                holder.itemView.setOnFocusChangeListener { v, hasFocus ->
                    if(hasFocus)   {
                        holder.itemView.isSelected = true
                        centerLayoutManager.smoothScrollToPosition(recycleView,
                            RecyclerView.State(),position)
                    }else{
                        holder.itemView.isSelected = false
                    }
                }

            }

            override fun getItemId(position: Int): Long {
                return position.toLong()
            }
        }.apply {
            setHasStableIds(true)
        }

//        adapter = object : CustomRecyclerView.CustomAdapter<LiveModel>(this,LiveModels){
//            override fun onSetViewHolder(view: View?): RecyclerView.ViewHolder {
//                return ViewHolder(this@MainActivity,view)
//            }
//
//            override fun onSetItemLayout(): Int {
//                return R.layout.live_item_view
//            }
//
//            override fun onSetItemData(viewHolder: RecyclerView.ViewHolder?, position: Int) {
//                viewHolder?.run {
//                    itemView.findViewById<TextView>(R.id.tv_title).text = LiveModels[position].title
//                    itemView.isSelected = curPosition == position
//                    itemView.setOnClickListener {
//                        curPosition = position
//                        switchPlay()
//                    }
//                }
//            }
//
//            override fun onItemFocus(itemView: View?, position: Int) {
//                curPosition = position
//                notifyDataSetChanged()
//            }
//
//            override fun onItemGetNormal(itemView: View?, position: Int) {
//
//            }
//
//            override fun getCount(): Int {
//                return LiveModels.size
//            }
//        }

        recycleView.adapter = adapter
    }

    private fun switchPlay(){
        adapter.notifyDataSetChanged()
        val model = LiveModels[curPosition]
        Log.d("MainActivity","play pos = "+ curPosition)
        video_player.setUp(model.playUrl,false,"")
        video_player.startPlayLogic()
    }

    private fun loadVideoData() {
        //中央电视台
        LiveModels.add((LiveModel("CCTV1直播","http://ivi.bupt.edu.cn/hls/cctv1hd.m3u8")))
        LiveModels.add((LiveModel("CCTV2直播","http://ivi.bupt.edu.cn/hls/cctv2hd.m3u8")))
        LiveModels.add((LiveModel("CCTV3直播","http://ivi.bupt.edu.cn/hls/cctv3hd.m3u8")))
        LiveModels.add((LiveModel("CCTV4直播","http://ivi.bupt.edu.cn/hls/cctv4hd.m3u8")))
        LiveModels.add((LiveModel("CCTV-5+","http://ivi.bupt.edu.cn/hls/cctv5phd.m3u8")))
        LiveModels.add((LiveModel("CCTV6直播","http://ivi.bupt.edu.cn/hls/cctv6hd.m3u8")))
        LiveModels.add((LiveModel("CCTV7直播","http://ivi.bupt.edu.cn/hls/cctv7hd.m3u8")))
        LiveModels.add((LiveModel("CCTV8直播","http://ivi.bupt.edu.cn/hls/cctv8hd.m3u8")))
        LiveModels.add((LiveModel("CCTV9直播","http://ivi.bupt.edu.cn/hls/cctv9hd.m3u8")))
        LiveModels.add((LiveModel("CCTV10直播","http://ivi.bupt.edu.cn/hls/cctv10hd.m3u8")))
        LiveModels.add((LiveModel("CCTV4K超高清","https://hls.cntv.baishancdnx.cn/asp/hls/8000/0303000a/3/default/9891cea600df4e8a83851b1b1ce23194/8000.m3u8")))
        LiveModels.add((LiveModel("CCTV12直播","http://ivi.bupt.edu.cn/hls/cctv12hd.m3u8")))
        LiveModels.add((LiveModel("CCTV13直播","http://ivi.bupt.edu.cn/hls/cctv13hd.m3u8")))
        LiveModels.add((LiveModel("CCTV-14高清","http://ivi.bupt.edu.cn/hls/cctv14hd.m3u8")))
        LiveModels.add((LiveModel("CCTV-17高清","http://ivi.bupt.edu.cn/hls/cctv17hd.m3u8")))
        LiveModels.add((LiveModel("CETV-1高清","http://ivi.bupt.edu.cn/hls/cetv1hd.m3u8")))
        LiveModels.add((LiveModel("CGTN高清","http://ivi.bupt.edu.cn/hls/cgtnhd.m3u8")))
        LiveModels.add((LiveModel("CGTN DOC高清","http://ivi.bupt.edu.cn/hls/cgtndochd.m3u8")))


        //少儿
        LiveModels.add((LiveModel("甘肃少儿蓝光","http://39.134.39.39/PLTV/88888888/224/3221226289/index.m3u8")))
        LiveModels.add((LiveModel("NewTV动画王国","http://223.110.245.161/ott.js.chinamobile.com/PLTV/3/224/3221225555/index.m3u8")))
        LiveModels.add((LiveModel("SiTV动漫秀场FHD","http://39.134.39.39/PLTV/88888888/224/3221226197/index.m3u8")))
        LiveModels.add((LiveModel("求索动物FHD","http://39.134.66.66/PLTV/88888888/224/3221225730/index.m3u8")))
        LiveModels.add((LiveModel("求索生活FHD","http://39.134.66.66/PLTV/88888888/224/3221225715/index.m3u8")))
        LiveModels.add((LiveModel("求索一","http://39.134.65.162/PLTV/88888888/224/3221225713/index.m3u8")))
        LiveModels.add((LiveModel("求索二","http://39.134.65.162/PLTV/88888888/224/3221225715/index.m3u8")))
        LiveModels.add((LiveModel("求索三","http://39.134.65.162/PLTV/88888888/224/3221225728/index.m3u8")))

        //音乐台
        LiveModels.add((LiveModel("CIBN流行榜单","http://v.iam7.cn/iptv/cibn.php?code=155")))
        LiveModels.add((LiveModel("CIBN古典音乐","http://v.iam7.cn/iptv/cibn.php?code=78")))
        LiveModels.add((LiveModel("CIBN经典音乐","http://v.iam7.cn/iptv/cibn.php?code=128")))
        LiveModels.add((LiveModel("CIBN女团专场","http://v.iam7.cn/iptv/cibn.php?code=208")))
        LiveModels.add((LiveModel("CIBN全球歌曲","http://v.iam7.cn/iptv/cibn.php?code=338")))
        LiveModels.add((LiveModel("CIBN音乐综艺","http://v.iam7.cn/iptv/cibn.php?code=145")))

        //电影电视剧
        LiveModels.add((LiveModel("CHC高清电影","http://ivi.bupt.edu.cn/hls/chchd.m3u8")))
        LiveModels.add((LiveModel("CHC家庭影院","http://dbiptv.sn.chinamobile.com/PLTV/88888890/224/3221226462/index.m3u8")))
        LiveModels.add((LiveModel("CIBN精品收藏","http://v.iam7.cn/iptv/cibn.php?code=501")))
        LiveModels.add((LiveModel("NewTV动作电影","http://ottrrs.hl.chinamobile.com/PLTV/88888888/224/3221225661/index.m3u8")))
        LiveModels.add((LiveModel("NewTV惊悚悬疑","http://39.134.66.66/PLTV/88888888/224/3221225553/index.m3u8")))
        LiveModels.add((LiveModel("NewTV精品电影","http://39.134.65.4:80/wh7f454c46tw1749080918_-1055122425/ottrrs.hl.chinamobile.com/PLTV/88888888/224/3221225667/index.m3u8?icpid=88888888&RTS=1554599521&from=1&hms_devid=4")))
        LiveModels.add((LiveModel("New功夫剧场","http://39.134.65.84:80/wh7f454c46tw1933122944_256139629/ottrrs.hl.chinamobile.com/PLTV/88888888/224/3221225681/index.m3u8?icpid=88888888&RTS=1579029479&from=1&hms_devid=130")))
        LiveModels.add((LiveModel("黑莓电影","http://121.31.30.90:8085/ysten-business/live/jdianying/yst.m3u8")))
        LiveModels.add((LiveModel("欢笑剧场4K","http://39.134.39.38/PLTV/88888888/224/3221226203/index.m3u8")))
        LiveModels.add((LiveModel("NewTV爱情喜剧","http://39.134.66.66/PLTV/88888888/224/3221225533/index.m3u8")))
        LiveModels.add((LiveModel("NewTV东北热剧","http://39.134.66.66/PLTV/88888888/224/3221225679/index.m3u8")))
        LiveModels.add((LiveModel("NewTV海外剧场","http://117.169.119.199:8080/live/xiqumd/index.m3u8")))
        LiveModels.add((LiveModel("NewTV军旅剧场","http://121.31.30.90:8085/ysten-business/live/junlvjc/1.m3u8")))
        LiveModels.add((LiveModel("SiTV都市剧场","http://39.134.39.39/PLTV/88888888/224/3221226176/index.m3u8")))
        LiveModels.add((LiveModel("SiTV欢笑剧场","http://39.134.39.39/PLTV/88888888/224/3221226203/index.m3u8")))
        LiveModels.add((LiveModel("北京影视","http://ivi.bupt.edu.cn/hls/btv4hd.m3u8")))
        LiveModels.add((LiveModel("IPTV明星大片","http://121.31.30.90:8085/ysten-business/live/jingpinjl/yst.m3u8")))


        //省台
        LiveModels.add((LiveModel("NEW山东","http://ottrrs.hl.chinamobile.com/PLTV/88888888/224/3221225788/index.m3u8")))
        LiveModels.add((LiveModel("NEW江西","http://ottrrs.hl.chinamobile.com/PLTV/88888888/224/3221225789/index.m3u8")))
        LiveModels.add((LiveModel("NEW安徽","http://ottrrs.hl.chinamobile.com/PLTV/88888888/224/3221225790/index.m3u8")))
        LiveModels.add((LiveModel("NEW广东","http://ottrrs.hl.chinamobile.com/PLTV/88888888/224/3221225791/index.m3u8")))
        LiveModels.add((LiveModel("NEW四川","http://ottrrs.hl.chinamobile.com/PLTV/88888888/224/3221225793/index.m3u8")))
        LiveModels.add((LiveModel("NEW吉林","http://ottrrs.hl.chinamobile.com/PLTV/88888888/224/3221225794/index.m3u8")))
        LiveModels.add((LiveModel("NEW广东","http://ottrrs.hl.chinamobile.com/PLTV/88888888/224/3221225791/index.m3u8")))
        LiveModels.add((LiveModel("IPTV5+蓝光","http://39.134.39.39/PLTV/88888888/224/3221226273/index.m3u8")))

        LiveModels.add((LiveModel("爱都市","http://101.71.255.229:6610/zjhs/2/10111/index.m3u8?virtualDomain=zjhs.live_hls.zte.com")))
        LiveModels.add((LiveModel("爱江湖","http://101.71.255.229:6610/zjhs/2/10114/index.m3u8?virtualDomain=zjhs.live_hls.zte.com")))
        LiveModels.add((LiveModel("爱解密","http://101.71.255.229:6610/zjhs/2/10109/index.m3u8?virtualDomain=zjhs.live_hls.zte.com")))
        LiveModels.add((LiveModel("爱经典","http://101.71.255.229:6610/zjhs/2/10106/index.m3u8?virtualDomain=zjhs.live_hls.zte.com")))
        LiveModels.add((LiveModel("爱军武","http://101.71.255.229:6610/zjhs/2/10119/index.m3u8?virtualDomain=zjhs.live_hls.zte.com")))
        LiveModels.add((LiveModel("爱浪漫","http://101.71.255.229:6610/zjhs/2/10115/index.m3u8?virtualDomain=zjhs.live_hls.zte.com")))
        LiveModels.add((LiveModel("爱历史","http://101.71.255.229:6610/zjhs/2/10120/index.m3u8?virtualDomain=zjhs.live_hls.zte.com")))
        LiveModels.add((LiveModel("爱美食","http://101.71.255.229:6610/zjhs/2/10108/index.m3u8?virtualDomain=zjhs.live_hls.zte.com")))
        LiveModels.add((LiveModel("爱奇谈","http://101.71.255.229:6610/zjhs/2/10103/index.m3u8?virtualDomain=zjhs.live_hls.zte.com")))
        LiveModels.add((LiveModel("爱时尚","http://101.71.255.229:6610/zjhs/2/10118/index.m3u8?virtualDomain=zjhs.live_hls.zte.com")))
        LiveModels.add((LiveModel("爱世界","http://101.71.255.229:6610/zjhs/2/10121/index.m3u8?virtualDomain=zjhs.live_hls.zte.com")))
        LiveModels.add((LiveModel("爱玩具","http://101.71.255.229:6610/zjhs/2/10117/index.m3u8?virtualDomain=zjhs.live_hls.zte.com")))
        LiveModels.add((LiveModel("爱喜剧","http://101.71.255.229:6610/zjhs/2/10105/index.m3u8?virtualDomain=zjhs.live_hls.zte.com")))
        LiveModels.add((LiveModel("爱谍战","http://39.134.176.167/hwltc.tv.cdn.zj.chinamobile.com/PLTV/88888888/224/3221230092/1.m3u8")))
        LiveModels.add((LiveModel("爱幼教","http://101.71.255.229:6610/zjhs/2/10112/index.m3u8?virtualDomain=zjhs.live_hls.zte.com")))

        LiveModels.add((LiveModel("NewTV搏击","http://ottrrs.hl.chinamobile.com/PLTV/88888888/224/3221225680/index.m3u8")))
        LiveModels.add((LiveModel("NewTV超级体育","http://39.134.66.66/PLTV/88888888/224/3221225635/index.m3u8")))
        LiveModels.add((LiveModel("NewTV军事频道","http://121.31.30.90:8085/ysten-business/live/junshipl/1.m3u8")))
        LiveModels.add((LiveModel("NewTV完美游戏","http://121.31.30.90:8085/ysten-business/live/wmyx/1.m3u8")))
        LiveModels.add((LiveModel("Newtv自由搏击","http://121.31.30.91:8081/ysten-business/live/SD-1500k-576P-bokesen/1.m3u8")))


        LiveModels.add((LiveModel("IPTV精品记录","http://121.31.30.90:8085/ysten-business/live/mingxingdp/yst.m3u8")))


        LiveModels.add((LiveModel("SiTV极速汽车","http://39.134.39.39/PLTV/88888888/224/3221226195/index.m3u8")))
        LiveModels.add((LiveModel("SiTV劲爆体育","http://39.134.39.39/PLTV/88888888/224/3221226170/index.m3u8")))

        LiveModels.add((LiveModel("广州番禺HD","http://live.pybtv.cn/channel1/sd/live.m3u8")))
        LiveModels.add((LiveModel("甘肃都市蓝光","http://39.134.39.39/PLTV/88888888/224/3221226248/index.m3u8")))
        LiveModels.add((LiveModel("甘肃公共蓝光","http://39.134.39.37/PLTV/88888888/224/3221226250/index.m3u8")))
        LiveModels.add((LiveModel("甘肃经济蓝光","http://39.134.39.39/PLTV/88888888/224/3221226252/index.m3u8")))
        LiveModels.add((LiveModel("甘肃文化影视蓝光","http://39.134.39.39/PLTV/88888888/224/3221226287/index.m3u8")))
        LiveModels.add((LiveModel("兰州公共频道蓝光","http://39.134.39.39/PLTV/88888888/224/3221226244/index.m3u8")))
        LiveModels.add((LiveModel("兰州生活经济蓝光","http://39.134.39.39/PLTV/88888888/224/3221226285/index.m3u8")))
        LiveModels.add((LiveModel("兰州新闻综合蓝光","http://39.134.39.39/PLTV/88888888/224/3221226242/index.m3u8")))
        LiveModels.add((LiveModel("兰州综艺体育蓝光","http://39.134.39.39/PLTV/88888888/224/3221226246/index.m3u8")))

        LiveModels.add((LiveModel("北京文艺FHD","http://ivi.bupt.edu.cn/hls/btv2hd.m3u8")))
        LiveModels.add((LiveModel("北京冬奥纪实蓝光","http://117.169.119.199:8080/live/HD-8000k-1080P-beijingjishi/index.m3u8")))
        LiveModels.add((LiveModel("中国交通","http://tv.lanjingfm.com/cctbn/hainan.m3u8")))
        LiveModels.add((LiveModel("第一财经HD","http://39.134.39.37/PLTV/88888888/224/3221226172/index.m3u8")))
        LiveModels.add((LiveModel("黄冈公共频道","http://huanggang.live.cjyun.org/video/s10120-xwgg.m3u8")))

        LiveModels.add((LiveModel("湖南卫视直播","http://ivi.bupt.edu.cn/hls/hunanhd.m3u8")))
        LiveModels.add((LiveModel("北京卫视直播","http://ivi.bupt.edu.cn/hls/btv1hd.m3u8")))
        LiveModels.add((LiveModel("东方卫视高清","http://ivi.bupt.edu.cn/hls/dfhd.m3u8")))
        LiveModels.add((LiveModel("北京文艺高清","http://ivi.bupt.edu.cn/hls/btv2hd.m3u8")))
        LiveModels.add((LiveModel("北京影视高清","http://ivi.bupt.edu.cn/hls/btv4hd.m3u8")))
        LiveModels.add((LiveModel("北京新闻高清","http://ivi.bupt.edu.cn/hls/btv9hd.m3u8")))
        LiveModels.add((LiveModel("北京冬奥纪实高清","http://ivi.bupt.edu.cn/hls/btv11hd.m3u8")))
        LiveModels.add((LiveModel("浙江卫视高清","http://ivi.bupt.edu.cn/hls/zjhd.m3u8")))
        LiveModels.add((LiveModel("江苏卫视高清","http://ivi.bupt.edu.cn/hls/jshd.m3u8")))
        LiveModels.add((LiveModel("安徽卫视高清","http://ivi.bupt.edu.cn/hls/ahhd.m3u8")))
        LiveModels.add((LiveModel("黑龙江卫视高清","http://ivi.bupt.edu.cn/hls/hljhd.m3u8")))
        LiveModels.add((LiveModel("辽宁卫视高清","http://ivi.bupt.edu.cn/hls/lnhd.m3u8")))
        LiveModels.add((LiveModel("深圳卫视高清","http://ivi.bupt.edu.cn/hls/szhd.m3u8")))
        LiveModels.add((LiveModel("广东卫视高清","http://ivi.bupt.edu.cn/hls/gdhd.m3u8")))
        LiveModels.add((LiveModel("天津卫视高清","http://ivi.bupt.edu.cn/hls/tjhd.m3u8")))
        LiveModels.add((LiveModel("湖北卫视高清","http://ivi.bupt.edu.cn/hls/hbhd.m3u8")))
        LiveModels.add((LiveModel("山东卫视高清","http://ivi.bupt.edu.cn/hls/sdhd.m3u8")))
        LiveModels.add((LiveModel("重庆卫视高清","http://ivi.bupt.edu.cn/hls/cqhd.m3u8")))
        LiveModels.add((LiveModel("东南卫视高清","http://ivi.bupt.edu.cn/hls/dnhd.m3u8")))
        LiveModels.add((LiveModel("北京纪实高清","http://ivi.bupt.edu.cn/hls/btv11hd.m3u8")))
        LiveModels.add((LiveModel("上海纪实高清","http://ivi.bupt.edu.cn/hls/docuchina.m3u8")))
        LiveModels.add((LiveModel("金鹰纪实高清","http://ivi.bupt.edu.cn/hls/gedocu.m3u8")))
        LiveModels.add((LiveModel("四川卫视高清","http://ivi.bupt.edu.cn/hls/schd.m3u8")))
        LiveModels.add((LiveModel("河北卫视高清","http://ivi.bupt.edu.cn/hls/hebhd.m3u8")))
        LiveModels.add((LiveModel("江西卫视高清","http://ivi.bupt.edu.cn/hls/jxhd.m3u8")))
        LiveModels.add((LiveModel("河南卫视高清","http://ivi.bupt.edu.cn/hls/hnhd.m3u8")))
        LiveModels.add((LiveModel("广西卫视高清","http://ivi.bupt.edu.cn/hls/gxhd.m3u8")))
        LiveModels.add((LiveModel("吉林卫视高清","http://ivi.bupt.edu.cn/hls/jlhd.m3u8")))
        LiveModels.add((LiveModel("海南卫视高清","http://ivi.bupt.edu.cn/hls/lyhd.m3u8")))
        LiveModels.add((LiveModel("贵州卫视高清","http://ivi.bupt.edu.cn/hls/gzhd.m3u8")))
    }

    private fun init() {

        //增加封面

        //增加封面
        val imageView = ImageView(this)
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
//        imageView.setImageResource(R.mipmap.xxx1);
        //        imageView.setImageResource(R.mipmap.xxx1);
        video_player.setThumbImageView(imageView)
        //增加title
        //增加title
        video_player.getTitleTextView().setVisibility(View.VISIBLE)
        //设置返回键
        //设置返回键
        video_player.getBackButton().setVisibility(View.VISIBLE)
        //设置旋转
        //设置旋转
        orientationUtils = OrientationUtils(this, video_player)
        //设置全屏按键功能,这是使用的是选择屏幕，而不是全屏
        //设置全屏按键功能,这是使用的是选择屏幕，而不是全屏
        video_player.getFullscreenButton()
            .setOnClickListener(View.OnClickListener { orientationUtils.resolveByClick() })
        //是否可以滑动调整
        //是否可以滑动调整
        video_player.setIsTouchWiget(false)
        //设置返回按键功能
        //设置返回按键功能
        video_player.getBackButton().setOnClickListener(View.OnClickListener { onBackPressed() })
    }

    override fun dispatchKeyEvent(event: KeyEvent): Boolean {
        if( event.action == KeyEvent.ACTION_UP){
            if(event.keyCode == KeyEvent.KEYCODE_DPAD_DOWN){
                if(recycleView.visibility == View.INVISIBLE){
                    recycleView.visibility = View.VISIBLE
                    centerLayoutManager.smoothScrollToPosition(recycleView,
                        RecyclerView.State(),curPosition)
                    window.decorView.post {
                        recycleView.requestSelectFocus(curPosition)
                    }
                }
            }else if(event.keyCode == KeyEvent.KEYCODE_DPAD_UP){

            }else if(event.keyCode == KeyEvent.KEYCODE_DPAD_LEFT){
                if(recycleView.visibility == View.INVISIBLE){
                    curPosition--;
                    if(curPosition < 0 ) curPosition = 0;
                    switchPlay()
                    return true
                }
            }else if(event.keyCode == KeyEvent.KEYCODE_DPAD_RIGHT){
                if(recycleView.visibility == View.INVISIBLE){
                    curPosition++;
                    if(curPosition > LiveModels.size - 1){
                        curPosition = 0;
                    }
                    Log.d("MainActivity","play pos KEYCODE_DPAD_RIGHT = "+ curPosition)
                    switchPlay()
                    return true
                }
            }else if(event.keyCode == KeyEvent.KEYCODE_ENTER){

            }else if(event.keyCode == KeyEvent.KEYCODE_BACK){
                if(recycleView.visibility == View.VISIBLE){
                    recycleView.visibility = View.INVISIBLE
                    return true
                }
            }
        }

        return super.dispatchKeyEvent(event)
    }
}