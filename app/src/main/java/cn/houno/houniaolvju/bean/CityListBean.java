package cn.houno.houniaolvju.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：HouNiaoLvJu
 * 类描述：城市列表bean
 * 创建人：qzc
 * 创建时间：2016/10/5 23:03
 * 修改人：qzc
 * 修改时间：2016/10/5 23:03
 * 修改备注：
 */
public class CityListBean {

    /**
     * status : 0
     * data : {"hot":[{"id":"8","name":"海口","row_number":"1"},{"id":"9","name":"三亚","row_number":"2"},{"id":"10","name":"万宁","row_number":"3"},{"id":"11","name":"广州","row_number":"4"},{"id":"12","name":"深圳","row_number":"5"},{"id":"58","name":"丽江","row_number":"6"},{"id":"59","name":"大理","row_number":"7"},{"id":"60","name":"桂林","row_number":"8"},{"id":"61","name":"南宁","row_number":"9"},{"id":"66","name":"西双版纳","row_number":"10"},{"id":"70","name":"承德","row_number":"11"},{"id":"88","name":"秦皇岛","row_number":"12"},{"id":"97","name":"成都","row_number":"13"},{"id":"103","name":"绵阳","row_number":"14"},{"id":"110","name":"涪陵","row_number":"15"},{"id":"115","name":"宜宾","row_number":"16"},{"id":"119","name":"乐山","row_number":"17"},{"id":"134","name":"太原","row_number":"18"},{"id":"135","name":"大同","row_number":"19"},{"id":"139","name":"拉萨","row_number":"20"},{"id":"141","name":"贵阳","row_number":"21"},{"id":"151","name":"昆明","row_number":"22"},{"id":"152","name":"大理","row_number":"23"},{"id":"154","name":"曲靖","row_number":"24"},{"id":"166","name":"临汾","row_number":"25"},{"id":"173","name":"文昌","row_number":"26"},{"id":"174","name":"琼海","row_number":"27"},{"id":"192","name":"沈阳","row_number":"28"},{"id":"193","name":"济南","row_number":"29"},{"id":"196","name":"青岛","row_number":"30"},{"id":"197","name":"抚顺","row_number":"31"},{"id":"201","name":"烟台","row_number":"32"},{"id":"217","name":"合肥","row_number":"33"},{"id":"223","name":"长春","row_number":"34"},{"id":"231","name":"黄山","row_number":"35"},{"id":"257","name":"福州","row_number":"36"},{"id":"259","name":"厦门","row_number":"37"},{"id":"294","name":"杭州","row_number":"38"},{"id":"317","name":"南京","row_number":"39"},{"id":"320","name":"镇江","row_number":"40"},{"id":"321","name":"咸阳","row_number":"41"},{"id":"325","name":"延安","row_number":"42"},{"id":"343","name":"北海","row_number":"43"},{"id":"363","name":"东莞","row_number":"44"},{"id":"383","name":"武汉","row_number":"45"},{"id":"392","name":"长沙","row_number":"46"},{"id":"394","name":"张家界","row_number":"47"},{"id":"403","name":"南昌","row_number":"48"},{"id":"419","name":"安阳","row_number":"49"},{"id":"420","name":"济源","row_number":"50"},{"id":"421","name":"新乡","row_number":"51"},{"id":"424","name":"洛阳","row_number":"52"},{"id":"432","name":"三门峡","row_number":"53"},{"id":"434","name":"郑州","row_number":"54"},{"id":"440","name":"保亭","row_number":"55"},{"id":"441","name":"陵水","row_number":"56"},{"id":"445","name":"海棠湾","row_number":"57"},{"id":"446","name":"亚龙湾","row_number":"58"},{"id":"447","name":"大东海","row_number":"59"},{"id":"448","name":"三亚湾","row_number":"60"},{"id":"449","name":"南山区","row_number":"61"},{"id":"450","name":"三亚市区","row_number":"62"},{"id":"451","name":"龙华区","row_number":"63"},{"id":"454","name":"景德镇","row_number":"64"},{"id":"461","name":"开封","row_number":"65"}],"other":{"A":[{"id":"355","name":"阿坝","alias":"aba","row_number":"1"},{"id":"302","name":"阿克苏地","alias":"akesudi","row_number":"2"},{"id":"187","name":"阿拉善盟","alias":"alashanmeng","row_number":"3"},{"id":"328","name":"安康","alias":"ankang","row_number":"4"},{"id":"224","name":"安庆","alias":"anqing","row_number":"5"},{"id":"195","name":"鞍山","alias":"anshan","row_number":"6"},{"id":"144","name":"安顺","alias":"anshun","row_number":"7"},{"id":"419","name":"安阳","alias":"anyang","row_number":"8"}],"B":[{"id":"245","name":"白城","alias":"baicheng","row_number":"9"},{"id":"380","name":"百色","alias":"baise","row_number":"10"},{"id":"242","name":"白山","alias":"baishan","row_number":"11"},{"id":"266","name":"白银市","alias":"baiyinshi","row_number":"12"},{"id":"219","name":"蚌埠","alias":"bangbu","row_number":"13"},{"id":"92","name":"保定","alias":"baoding","row_number":"14"},{"id":"319","name":"宝鸡","alias":"baoji","row_number":"15"},{"id":"126","name":"宝坻区","alias":"baoqu","row_number":"16"},{"id":"155","name":"保山","alias":"baoshan","row_number":"17"},{"id":"440","name":"保亭","alias":"baoting","row_number":"18"},{"id":"172","name":"包头","alias":"baotou","row_number":"19"},{"id":"181","name":"巴彦淖尔","alias":"bayannaoer","row_number":"20"},{"id":"311","name":"巴音郭楞蒙古自治州　","alias":"bayinguolengmengguzizhizhou","row_number":"21"},{"id":"356","name":"巴中","alias":"bazhong","row_number":"22"},{"id":"122","name":"北辰区","alias":"beichenqu","row_number":"23"},{"id":"343","name":"北海","alias":"beihai","row_number":"24"},{"id":"200","name":"本溪","alias":"benxi","row_number":"25"},{"id":"148","name":"毕节","alias":"bijie","row_number":"26"},{"id":"114","name":"滨海新区","alias":"binhaixinqu","row_number":"27"},{"id":"401","name":"滨州","alias":"binzhou","row_number":"28"},{"id":"309","name":"博尔塔拉蒙古自治州","alias":"boertalamengguzizhizhou","row_number":"29"}],"C":[{"id":"94","name":"沧州","alias":"cangzhou","row_number":"30"},{"id":"223","name":"长春","alias":"changchun","row_number":"31"},{"id":"393","name":"常德","alias":"changde","row_number":"32"},{"id":"465","name":"长葛","alias":"changge","row_number":"33"},{"id":"429","name":"昌江县","alias":"changjiangxian","row_number":"34"},{"id":"308","name":"昌吉回族自治州","alias":"changjihuizuzizhizhou","row_number":"35"},{"id":"392","name":"长沙","alias":"changsha","row_number":"36"},{"id":"339","name":"常熟","alias":"changshu","row_number":"37"},{"id":"140","name":"长治","alias":"changzhi","row_number":"38"},{"id":"337","name":"常州","alias":"changzhou","row_number":"39"},{"id":"254","name":"巢湖","alias":"chaohu","row_number":"40"},{"id":"216","name":"朝阳","alias":"chaoyang","row_number":"41"},{"id":"360","name":"潮州","alias":"chaozhou","row_number":"42"},{"id":"70","name":"承德","alias":"chengde","row_number":"43"},{"id":"97","name":"成都","alias":"chengdu","row_number":"44"},{"id":"431","name":"澄迈县","alias":"chengmaixian","row_number":"45"},{"id":"464","name":"郴州","alias":"chenzhou","row_number":"46"},{"id":"177","name":"赤峰","alias":"chifeng","row_number":"47"},{"id":"414","name":"池州","alias":"chizhou","row_number":"48"},{"id":"160","name":"楚雄","alias":"chuxiong","row_number":"49"},{"id":"235","name":"滁州","alias":"chuzhou","row_number":"50"}],"D":[{"id":"447","name":"大东海","alias":"dadonghai","row_number":"51"},{"id":"59","name":"大理","alias":"dali","row_number":"52"},{"id":"152","name":"大理","alias":"dali","row_number":"53"},{"id":"194","name":"大连","alias":"dalian","row_number":"54"},{"id":"202","name":"丹东","alias":"dandong","row_number":"55"},{"id":"105","name":"达且","alias":"daqie","row_number":"56"},{"id":"280","name":"大庆","alias":"daqing","row_number":"57"},{"id":"135","name":"大同","alias":"datong","row_number":"58"},{"id":"293","name":"大兴安岭","alias":"daxinganling","row_number":"59"},{"id":"127","name":"德阳","alias":"deyang","row_number":"60"},{"id":"199","name":"德州","alias":"dezhou","row_number":"61"},{"id":"277","name":"定西市","alias":"dingxishi","row_number":"62"},{"id":"435","name":"迪庆藏族自治州","alias":"diqingcangzuzizhizhou","row_number":"63"},{"id":"363","name":"东莞","alias":"dong","row_number":"64"},{"id":"116","name":"东丽区","alias":"dongliqu","row_number":"65"},{"id":"145","name":"都匀","alias":"duyun","row_number":"66"}],"E":[{"id":"179","name":"鄂尔多斯","alias":"eerduosi","row_number":"67"},{"id":"487","name":"额尔古纳","alias":"eerguna","row_number":"68"}],"F":[{"id":"361","name":"佛山","alias":"foshan","row_number":"69"},{"id":"110","name":"涪陵","alias":"fuling","row_number":"70"},{"id":"197","name":"抚顺","alias":"fushun","row_number":"71"},{"id":"209","name":"阜新","alias":"fuxin","row_number":"72"},{"id":"227","name":"阜阳","alias":"fuyang","row_number":"73"},{"id":"257","name":"福州","alias":"fuzhou","row_number":"74"}],"G":[{"id":"288","name":"甘南藏族自治州","alias":"gannancangzuzizhizhou","row_number":"75"},{"id":"409","name":"赣州","alias":"ganzhou","row_number":"76"},{"id":"377","name":"甘孜","alias":"ganzi","row_number":"77"},{"id":"153","name":"个旧","alias":"gejiu","row_number":"78"},{"id":"258","name":"公主岭","alias":"gongzhuling","row_number":"79"},{"id":"128","name":"广元","alias":"guangyuan","row_number":"80"},{"id":"11","name":"广州","alias":"guangzhou","row_number":"81"},{"id":"60","name":"桂林","alias":"guilin","row_number":"82"},{"id":"141","name":"贵阳","alias":"guiyang","row_number":"83"},{"id":"248","name":"果洛","alias":"guoluo","row_number":"84"},{"id":"233","name":"固原市","alias":"guyuanshi","row_number":"85"}],"H":[{"id":"261","name":"哈尔滨","alias":"haerbin","row_number":"86"},{"id":"243","name":"海北","alias":"haibei","row_number":"87"},{"id":"240","name":"海东市","alias":"haidongshi","row_number":"88"},{"id":"8","name":"海口","alias":"haikou","row_number":"89"},{"id":"246","name":"海南州","alias":"hainanzhou","row_number":"90"},{"id":"445","name":"海棠湾","alias":"haitangwan","row_number":"91"},{"id":"251","name":"海西","alias":"haixi","row_number":"92"},{"id":"301","name":"哈密","alias":"hami","row_number":"93"},{"id":"90","name":"邯郸","alias":"handan","row_number":"94"},{"id":"294","name":"杭州","alias":"hangzhou","row_number":"95"},{"id":"330","name":"汉中","alias":"hanzhong","row_number":"96"},{"id":"111","name":"河北区","alias":"hebeiqu","row_number":"97"},{"id":"489","name":"鹤壁","alias":"hebi","row_number":"98"},{"id":"106","name":"河东区","alias":"hedongqu","row_number":"99"},{"id":"217","name":"合肥","alias":"hefei","row_number":"100"},{"id":"275","name":"鹤岗","alias":"hegang","row_number":"101"},{"id":"291","name":"黑河","alias":"heihe","row_number":"102"},{"id":"98","name":"衡水","alias":"hengshui","row_number":"103"},{"id":"463","name":"衡阳","alias":"hengyang","row_number":"104"},{"id":"306","name":"和田地","alias":"hetiandi","row_number":"105"},{"id":"108","name":"河西区","alias":"hexiqu","row_number":"106"},{"id":"370","name":"河源","alias":"heyuan","row_number":"107"},{"id":"210","name":"菏泽","alias":"heze","row_number":"108"},{"id":"346","name":"贺州","alias":"hezhou","row_number":"109"},{"id":"112","name":"红桥区","alias":"hongqiaoqu","row_number":"110"},{"id":"334","name":"淮安","alias":"huaian","row_number":"111"},{"id":"237","name":"淮北","alias":"huaibei","row_number":"112"},{"id":"396","name":"怀化","alias":"huaihua","row_number":"113"},{"id":"221","name":"淮南","alias":"huainan","row_number":"114"},{"id":"389","name":"黄冈","alias":"huanggang","row_number":"115"},{"id":"244","name":"黄南","alias":"huangnan","row_number":"116"},{"id":"231","name":"黄山","alias":"huangshan","row_number":"117"},{"id":"170","name":"呼和浩特","alias":"huhehaote","row_number":"118"},{"id":"362","name":"惠州","alias":"huizhou","row_number":"119"},{"id":"218","name":"葫芦岛","alias":"huludao","row_number":"120"},{"id":"180","name":"呼伦贝尔","alias":"hulunbeier","row_number":"121"},{"id":"113","name":"沪州","alias":"huzhou","row_number":"122"},{"id":"295","name":"湖州","alias":"huzhou","row_number":"123"}],"J":[{"id":"285","name":"佳木斯","alias":"jiamusi","row_number":"124"},{"id":"406","name":"吉安","alias":"jian","row_number":"125"},{"id":"371","name":"江门","alias":"jiangmen","row_number":"126"},{"id":"467","name":"焦作","alias":"jiaozuo","row_number":"127"},{"id":"297","name":"嘉兴","alias":"jiaxing","row_number":"128"},{"id":"263","name":"嘉峪关","alias":"jiayuguan","row_number":"129"},{"id":"225","name":"吉林","alias":"jilin","row_number":"130"},{"id":"255","name":"吉林省长白山保护开发区","alias":"jilinshengchangbaishanbaohukaifaqu","row_number":"131"},{"id":"193","name":"济南","alias":"jinan","row_number":"132"},{"id":"265","name":"金昌市","alias":"jinchangshi","row_number":"133"},{"id":"156","name":"晋城","alias":"jincheng","row_number":"134"},{"id":"454","name":"景德镇","alias":"jingdezhen","row_number":"135"},{"id":"130","name":"静海区","alias":"jinghaiqu","row_number":"136"},{"id":"390","name":"荆州","alias":"jingzhou","row_number":"137"},{"id":"312","name":"金华","alias":"jinhua","row_number":"138"},{"id":"205","name":"济宁","alias":"jining","row_number":"139"},{"id":"121","name":"津南区","alias":"jinnanqu","row_number":"140"},{"id":"163","name":"晋中","alias":"jinzhong","row_number":"141"},{"id":"204","name":"锦州","alias":"jinzhou","row_number":"142"},{"id":"410","name":"九江","alias":"jiujiang","row_number":"143"},{"id":"271","name":"酒泉市","alias":"jiuquanshi","row_number":"144"},{"id":"272","name":"鸡西","alias":"jixi","row_number":"145"},{"id":"133","name":"蓟县","alias":"jixian","row_number":"146"},{"id":"420","name":"济源","alias":"jiyuan","row_number":"147"}],"K":[{"id":"461","name":"开封","alias":"kaifeng","row_number":"148"},{"id":"146","name":"凯里","alias":"kaili","row_number":"149"},{"id":"304","name":"喀什地","alias":"kashidi","row_number":"150"},{"id":"298","name":"克拉玛依","alias":"kelamayi","row_number":"151"},{"id":"313","name":"克孜勒苏柯尔克孜自治州","alias":"kezilesukeerkezizizhizhou","row_number":"152"},{"id":"348","name":"昆明","alias":"kunming","row_number":"153"},{"id":"151","name":"昆明","alias":"kunming","row_number":"154"}],"L":[{"id":"96","name":"廊坊","alias":"langfang","row_number":"155"},{"id":"262","name":"兰州市","alias":"lanzhoushi","row_number":"156"},{"id":"139","name":"拉萨","alias":"lasa","row_number":"157"},{"id":"430","name":"乐东县","alias":"ledongxian","row_number":"158"},{"id":"119","name":"乐山","alias":"leshan","row_number":"159"},{"id":"376","name":"凉山","alias":"liangshan","row_number":"160"},{"id":"335","name":"连云港","alias":"lianyungang","row_number":"161"},{"id":"211","name":"辽阳","alias":"liaoyang","row_number":"162"},{"id":"234","name":"辽源","alias":"liaoyuan","row_number":"163"},{"id":"58","name":"丽江","alias":"lijiang","row_number":"164"},{"id":"166","name":"临汾","alias":"linfen","row_number":"165"},{"id":"466","name":"临高","alias":"lingao","row_number":"166"},{"id":"441","name":"陵水","alias":"lingshui","row_number":"167"},{"id":"286","name":"临夏回族自治州","alias":"linxiahuizuzizhizhou","row_number":"168"},{"id":"208","name":"临沂","alias":"linyi","row_number":"169"},{"id":"310","name":"丽水","alias":"lishui","row_number":"170"},{"id":"253","name":"六安","alias":"liuan","row_number":"171"},{"id":"149","name":"六盘水","alias":"liupanshui","row_number":"172"},{"id":"344","name":"柳州","alias":"liuzhou","row_number":"173"},{"id":"451","name":"龙华区","alias":"longhuaqu","row_number":"174"},{"id":"279","name":"陇南市","alias":"longnanshi","row_number":"175"},{"id":"276","name":"龙岩","alias":"longyan","row_number":"176"},{"id":"397","name":"娄底","alias":"loudi","row_number":"177"},{"id":"424","name":"洛阳","alias":"luoyang","row_number":"178"},{"id":"167","name":"吕梁","alias":"lvliang","row_number":"179"}],"M":[{"id":"222","name":"马鞍山","alias":"maanshan","row_number":"180"},{"id":"124","name":"马尔康","alias":"maerkang","row_number":"181"},{"id":"373","name":"茂名","alias":"maoming","row_number":"182"},{"id":"256","name":"梅河口","alias":"meihekou","row_number":"183"},{"id":"492","name":"美兰区","alias":"meilanqu","row_number":"184"},{"id":"378","name":"眉山","alias":"meishan","row_number":"185"},{"id":"372","name":"梅州","alias":"meizhou","row_number":"186"},{"id":"103","name":"绵阳","alias":"mianyang","row_number":"187"},{"id":"290","name":"牡丹江","alias":"mudanjiang","row_number":"188"}],"N":[{"id":"403","name":"南昌","alias":"nanchang","row_number":"189"},{"id":"379","name":"南充","alias":"nanchong","row_number":"190"},{"id":"317","name":"南京","alias":"nanjing","row_number":"191"},{"id":"109","name":"南开区","alias":"nankaiqu","row_number":"192"},{"id":"61","name":"南宁","alias":"nanning","row_number":"193"},{"id":"289","name":"南平","alias":"nanping","row_number":"194"},{"id":"449","name":"南山区","alias":"nanshanqu","row_number":"195"},{"id":"327","name":"南通","alias":"nantong","row_number":"196"},{"id":"462","name":"南阳","alias":"nanyang","row_number":"197"},{"id":"117","name":"内江","alias":"neijiang","row_number":"198"},{"id":"300","name":"宁波","alias":"ningbo","row_number":"199"},{"id":"260","name":"宁德","alias":"ningde","row_number":"200"},{"id":"129","name":"宁河区","alias":"ninghequ","row_number":"201"}],"P":[{"id":"213","name":"盘锦","alias":"panjin","row_number":"202"},{"id":"99","name":"攀枝花","alias":"panzhihua","row_number":"203"},{"id":"498","name":"平顶山","alias":"pingdingshan","row_number":"204"},{"id":"281","name":"平凉市","alias":"pingliangshi","row_number":"205"},{"id":"407","name":"萍乡","alias":"pingxiang","row_number":"206"},{"id":"352","name":"普洱","alias":"puer","row_number":"207"},{"id":"264","name":"莆田","alias":"putian","row_number":"208"}],"Q":[{"id":"411","name":"黔东南","alias":"qiandongnan","row_number":"209"},{"id":"413","name":"黔南","alias":"qiannan","row_number":"210"},{"id":"412","name":"黔西南","alias":"qianxinan","row_number":"211"},{"id":"196","name":"青岛","alias":"qingdao","row_number":"212"},{"id":"283","name":"庆阳市","alias":"qingyangshi","row_number":"213"},{"id":"369","name":"清远","alias":"qingyuan","row_number":"214"},{"id":"88","name":"秦皇岛","alias":"qinhuangdao","row_number":"215"},{"id":"381","name":"钦州","alias":"qinzhou","row_number":"216"},{"id":"174","name":"琼海","alias":"qionghaishi","row_number":"217"},{"id":"268","name":"齐齐哈尔","alias":"qiqihaer","row_number":"218"},{"id":"287","name":"七台河","alias":"qitaihe","row_number":"219"},{"id":"267","name":"泉州","alias":"quanzhou","row_number":"220"},{"id":"354","name":"曲靖","alias":"qujing","row_number":"221"},{"id":"154","name":"曲靖","alias":"qujing","row_number":"222"}],"R":[{"id":"214","name":"日照","alias":"rizhao","row_number":"223"}],"S":[{"id":"432","name":"三门峡","alias":"sanmenxia","row_number":"224"},{"id":"284","name":"三明","alias":"sanming","row_number":"225"},{"id":"9","name":"三亚","alias":"sanya","row_number":"226"},{"id":"450","name":"三亚市区","alias":"sanyashiqu","row_number":"227"},{"id":"448","name":"三亚湾","alias":"sanyawan","row_number":"228"},{"id":"331","name":"商洛","alias":"shangluo","row_number":"229"},{"id":"404","name":"上饶","alias":"shangrao","row_number":"230"},{"id":"359","name":"汕头","alias":"shantou","row_number":"231"},{"id":"368","name":"韶关","alias":"shaoguan","row_number":"232"},{"id":"303","name":"绍兴","alias":"shaoxing","row_number":"233"},{"id":"12","name":"深圳","alias":"shen","row_number":"234"},{"id":"387","name":"神农架","alias":"shennongjia","row_number":"235"},{"id":"192","name":"沈阳","alias":"shenyang","row_number":"236"},{"id":"69","name":"石家庄","alias":"shijiazhuang","row_number":"237"},{"id":"386","name":"十堰","alias":"shiyan","row_number":"238"},{"id":"230","name":"石嘴山市","alias":"shizuishanshi","row_number":"239"},{"id":"278","name":"双鸭山","alias":"shuangyashan","row_number":"240"},{"id":"158","name":"朔州","alias":"shuozhou","row_number":"241"},{"id":"422","name":"四会","alias":"sihui","row_number":"242"},{"id":"161","name":"思茅","alias":"simao","row_number":"243"},{"id":"229","name":"四平","alias":"siping","row_number":"244"},{"id":"247","name":"松原","alias":"songyuan","row_number":"245"},{"id":"292","name":"绥化","alias":"suihua","row_number":"246"},{"id":"357","name":"宿迁","alias":"suqian","row_number":"247"},{"id":"323","name":"苏州","alias":"suzhou","row_number":"248"},{"id":"226","name":"宿州","alias":"suzhou","row_number":"249"}],"T":[{"id":"207","name":"泰安","alias":"taian","row_number":"250"},{"id":"134","name":"太原","alias":"taiyuan","row_number":"251"},{"id":"340","name":"泰州","alias":"taizhou","row_number":"252"},{"id":"305","name":"台州","alias":"taizhou","row_number":"253"},{"id":"87","name":"唐山","alias":"tangshan","row_number":"254"},{"id":"351","name":"腾冲","alias":"tengchong","row_number":"255"},{"id":"269","name":"天水市","alias":"tianshuishi","row_number":"256"},{"id":"215","name":"铁岭","alias":"tieling","row_number":"257"},{"id":"324","name":"铜川","alias":"tongchuan","row_number":"258"},{"id":"238","name":"通化","alias":"tonghua","row_number":"259"},{"id":"178","name":"通辽","alias":"tongliao","row_number":"260"},{"id":"241","name":"铜陵","alias":"tongling","row_number":"261"},{"id":"147","name":"铜仁","alias":"tongren","row_number":"262"},{"id":"299","name":"吐鲁番","alias":"tulufan","row_number":"263"}],"W":[{"id":"10","name":"万宁","alias":"wanning","row_number":"264"},{"id":"107","name":"万县市","alias":"wanxianshi","row_number":"265"},{"id":"203","name":"潍坊","alias":"weifang","row_number":"266"},{"id":"212","name":"威海","alias":"weihai","row_number":"267"},{"id":"322","name":"渭南","alias":"weinan","row_number":"268"},{"id":"173","name":"文昌","alias":"wenchangshi","row_number":"269"},{"id":"102","name":"温江","alias":"wenjiang","row_number":"270"},{"id":"157","name":"文山","alias":"wenshan","row_number":"271"},{"id":"374","name":"文山","alias":"wenshan","row_number":"272"},{"id":"307","name":"温州","alias":"wenzhou","row_number":"273"},{"id":"175","name":"乌海","alias":"wuhai","row_number":"274"},{"id":"383","name":"武汉","alias":"wuhan","row_number":"275"},{"id":"220","name":"芜湖","alias":"wuhu","row_number":"276"},{"id":"182","name":"乌兰察布","alias":"wulanchabu","row_number":"277"},{"id":"296","name":"乌鲁木齐","alias":"wulumuqi","row_number":"278"},{"id":"125","name":"武清区","alias":"wuqingqu","row_number":"279"},{"id":"274","name":"武威市","alias":"wuweishi","row_number":"280"},{"id":"338","name":"无锡","alias":"wuxi","row_number":"281"},{"id":"171","name":"五指山市","alias":"wuzhishanshi","row_number":"282"},{"id":"232","name":"吴忠市","alias":"wuzhongshi","row_number":"283"},{"id":"347","name":"梧州","alias":"wuzhou","row_number":"284"}],"X":[{"id":"259","name":"厦门","alias":"xiamen","row_number":"285"},{"id":"318","name":"西安","alias":"xian","row_number":"286"},{"id":"350","name":"香格里拉","alias":"xianggelila","row_number":"287"},{"id":"395","name":"湘潭","alias":"xiangtan","row_number":"288"},{"id":"400","name":"湘西","alias":"xiangxi","row_number":"289"},{"id":"388","name":"襄阳","alias":"xiangyang","row_number":"290"},{"id":"321","name":"咸阳","alias":"xianyang","row_number":"291"},{"id":"385","name":"孝感","alias":"xiaogan","row_number":"292"},{"id":"120","name":"西昌","alias":"xichang","row_number":"293"},{"id":"184","name":"兴安盟","alias":"xinganmeng","row_number":"294"},{"id":"91","name":"邢台","alias":"xingtai","row_number":"295"},{"id":"150","name":"兴义","alias":"xingyi","row_number":"296"},{"id":"239","name":"西宁市","alias":"xiningshi","row_number":"297"},{"id":"421","name":"新乡","alias":"xinxiang","row_number":"298"},{"id":"423","name":"信阳","alias":"xinyang","row_number":"299"},{"id":"165","name":"忻州","alias":"xinzhou","row_number":"300"},{"id":"118","name":"西青区","alias":"xiqingqu","row_number":"301"},{"id":"66","name":"西双版纳","alias":"xishuangbanna","row_number":"302"},{"id":"452","name":"秀英区","alias":"xiuyingqu","row_number":"303"},{"id":"249","name":"宣城","alias":"xuancheng","row_number":"304"},{"id":"469","name":"许昌","alias":"xuchang","row_number":"305"},{"id":"333","name":"徐州","alias":"xuzhou","row_number":"306"}],"Y":[{"id":"123","name":"雅安","alias":"yaan","row_number":"307"},{"id":"446","name":"亚龙湾","alias":"yalongwan","row_number":"308"},{"id":"325","name":"延安","alias":"yanan","row_number":"309"},{"id":"252","name":"延边朝鲜族自治州","alias":"yanbianchaoxianzuzizhizhou","row_number":"310"},{"id":"332","name":"盐城","alias":"yancheng","row_number":"311"},{"id":"366","name":"阳江","alias":"yangjiang","row_number":"312"},{"id":"138","name":"阳泉","alias":"yangquan","row_number":"313"},{"id":"329","name":"扬州","alias":"yangzhou","row_number":"314"},{"id":"201","name":"烟台","alias":"yantai","row_number":"315"},{"id":"115","name":"宜宾","alias":"yibin","row_number":"316"},{"id":"384","name":"宜昌","alias":"yichang","row_number":"317"},{"id":"282","name":"伊春","alias":"yichun","row_number":"318"},{"id":"408","name":"宜春","alias":"yichun","row_number":"319"},{"id":"314","name":"伊犁哈萨克自治州（副省级自治州）","alias":"yilihasakezizhizhoufushengjizizhizhou","row_number":"320"},{"id":"228","name":"银川市","alias":"yinchuanshi","row_number":"321"},{"id":"206","name":"营口","alias":"yingkou","row_number":"322"},{"id":"405","name":"鹰潭","alias":"yingtan","row_number":"323"},{"id":"398","name":"益阳","alias":"yiyang","row_number":"324"},{"id":"101","name":"永川","alias":"yongchuan","row_number":"325"},{"id":"399","name":"岳阳","alias":"yueyang","row_number":"326"},{"id":"326","name":"榆林","alias":"yulin","row_number":"327"},{"id":"345","name":"玉林","alias":"yulin","row_number":"328"},{"id":"164","name":"运城","alias":"yuncheng","row_number":"329"},{"id":"250","name":"玉树","alias":"yushu","row_number":"330"},{"id":"353","name":"玉溪","alias":"yuxi","row_number":"331"},{"id":"159","name":"玉溪","alias":"yuxi","row_number":"332"}],"Z":[{"id":"455","name":"枣庄","alias":"zaozhuang","row_number":"333"},{"id":"394","name":"张家界","alias":"zhangjiajie","row_number":"334"},{"id":"93","name":"张家口","alias":"zhangjiakou","row_number":"335"},{"id":"273","name":"张掖市","alias":"zhangyeshi","row_number":"336"},{"id":"270","name":"漳州","alias":"zhangzhou","row_number":"337"},{"id":"367","name":"湛江","alias":"zhanjiang","row_number":"338"},{"id":"365","name":"肇庆","alias":"zhaoqing","row_number":"339"},{"id":"375","name":"昭通","alias":"zhaotong","row_number":"340"},{"id":"162","name":"昭通","alias":"zhaotong","row_number":"341"},{"id":"434","name":"郑州","alias":"zhengzhou","row_number":"342"},{"id":"320","name":"镇江","alias":"zhenjiang","row_number":"343"},{"id":"364","name":"中山","alias":"zhongshan","row_number":"344"},{"id":"236","name":"中卫市","alias":"zhongweishi","row_number":"345"},{"id":"315","name":"衢州","alias":"zhou","row_number":"346"},{"id":"316","name":"舟山","alias":"zhoushan","row_number":"347"},{"id":"169","name":"儋州市","alias":"zhoushi","row_number":"348"},{"id":"358","name":"珠海","alias":"zhuhai","row_number":"349"},{"id":"453","name":"株洲","alias":"zhuzhou","row_number":"350"},{"id":"198","name":"淄博","alias":"zibo","row_number":"351"},{"id":"100","name":"自贡","alias":"zigong","row_number":"352"},{"id":"143","name":"遵义","alias":"zunyi","row_number":"353"}]}}
     */

    private int status;
    /**
     * hot : [{"id":"8","name":"海口","row_number":"1"},{"id":"9","name":"三亚","row_number":"2"},{"id":"10","name":"万宁","row_number":"3"},{"id":"11","name":"广州","row_number":"4"},{"id":"12","name":"深圳","row_number":"5"},{"id":"58","name":"丽江","row_number":"6"},{"id":"59","name":"大理","row_number":"7"},{"id":"60","name":"桂林","row_number":"8"},{"id":"61","name":"南宁","row_number":"9"},{"id":"66","name":"西双版纳","row_number":"10"},{"id":"70","name":"承德","row_number":"11"},{"id":"88","name":"秦皇岛","row_number":"12"},{"id":"97","name":"成都","row_number":"13"},{"id":"103","name":"绵阳","row_number":"14"},{"id":"110","name":"涪陵","row_number":"15"},{"id":"115","name":"宜宾","row_number":"16"},{"id":"119","name":"乐山","row_number":"17"},{"id":"134","name":"太原","row_number":"18"},{"id":"135","name":"大同","row_number":"19"},{"id":"139","name":"拉萨","row_number":"20"},{"id":"141","name":"贵阳","row_number":"21"},{"id":"151","name":"昆明","row_number":"22"},{"id":"152","name":"大理","row_number":"23"},{"id":"154","name":"曲靖","row_number":"24"},{"id":"166","name":"临汾","row_number":"25"},{"id":"173","name":"文昌","row_number":"26"},{"id":"174","name":"琼海","row_number":"27"},{"id":"192","name":"沈阳","row_number":"28"},{"id":"193","name":"济南","row_number":"29"},{"id":"196","name":"青岛","row_number":"30"},{"id":"197","name":"抚顺","row_number":"31"},{"id":"201","name":"烟台","row_number":"32"},{"id":"217","name":"合肥","row_number":"33"},{"id":"223","name":"长春","row_number":"34"},{"id":"231","name":"黄山","row_number":"35"},{"id":"257","name":"福州","row_number":"36"},{"id":"259","name":"厦门","row_number":"37"},{"id":"294","name":"杭州","row_number":"38"},{"id":"317","name":"南京","row_number":"39"},{"id":"320","name":"镇江","row_number":"40"},{"id":"321","name":"咸阳","row_number":"41"},{"id":"325","name":"延安","row_number":"42"},{"id":"343","name":"北海","row_number":"43"},{"id":"363","name":"东莞","row_number":"44"},{"id":"383","name":"武汉","row_number":"45"},{"id":"392","name":"长沙","row_number":"46"},{"id":"394","name":"张家界","row_number":"47"},{"id":"403","name":"南昌","row_number":"48"},{"id":"419","name":"安阳","row_number":"49"},{"id":"420","name":"济源","row_number":"50"},{"id":"421","name":"新乡","row_number":"51"},{"id":"424","name":"洛阳","row_number":"52"},{"id":"432","name":"三门峡","row_number":"53"},{"id":"434","name":"郑州","row_number":"54"},{"id":"440","name":"保亭","row_number":"55"},{"id":"441","name":"陵水","row_number":"56"},{"id":"445","name":"海棠湾","row_number":"57"},{"id":"446","name":"亚龙湾","row_number":"58"},{"id":"447","name":"大东海","row_number":"59"},{"id":"448","name":"三亚湾","row_number":"60"},{"id":"449","name":"南山区","row_number":"61"},{"id":"450","name":"三亚市区","row_number":"62"},{"id":"451","name":"龙华区","row_number":"63"},{"id":"454","name":"景德镇","row_number":"64"},{"id":"461","name":"开封","row_number":"65"}]
     * other : {"A":[{"id":"355","name":"阿坝","alias":"aba","row_number":"1"},{"id":"302","name":"阿克苏地","alias":"akesudi","row_number":"2"},{"id":"187","name":"阿拉善盟","alias":"alashanmeng","row_number":"3"},{"id":"328","name":"安康","alias":"ankang","row_number":"4"},{"id":"224","name":"安庆","alias":"anqing","row_number":"5"},{"id":"195","name":"鞍山","alias":"anshan","row_number":"6"},{"id":"144","name":"安顺","alias":"anshun","row_number":"7"},{"id":"419","name":"安阳","alias":"anyang","row_number":"8"}],"B":[{"id":"245","name":"白城","alias":"baicheng","row_number":"9"},{"id":"380","name":"百色","alias":"baise","row_number":"10"},{"id":"242","name":"白山","alias":"baishan","row_number":"11"},{"id":"266","name":"白银市","alias":"baiyinshi","row_number":"12"},{"id":"219","name":"蚌埠","alias":"bangbu","row_number":"13"},{"id":"92","name":"保定","alias":"baoding","row_number":"14"},{"id":"319","name":"宝鸡","alias":"baoji","row_number":"15"},{"id":"126","name":"宝坻区","alias":"baoqu","row_number":"16"},{"id":"155","name":"保山","alias":"baoshan","row_number":"17"},{"id":"440","name":"保亭","alias":"baoting","row_number":"18"},{"id":"172","name":"包头","alias":"baotou","row_number":"19"},{"id":"181","name":"巴彦淖尔","alias":"bayannaoer","row_number":"20"},{"id":"311","name":"巴音郭楞蒙古自治州　","alias":"bayinguolengmengguzizhizhou","row_number":"21"},{"id":"356","name":"巴中","alias":"bazhong","row_number":"22"},{"id":"122","name":"北辰区","alias":"beichenqu","row_number":"23"},{"id":"343","name":"北海","alias":"beihai","row_number":"24"},{"id":"200","name":"本溪","alias":"benxi","row_number":"25"},{"id":"148","name":"毕节","alias":"bijie","row_number":"26"},{"id":"114","name":"滨海新区","alias":"binhaixinqu","row_number":"27"},{"id":"401","name":"滨州","alias":"binzhou","row_number":"28"},{"id":"309","name":"博尔塔拉蒙古自治州","alias":"boertalamengguzizhizhou","row_number":"29"}],"C":[{"id":"94","name":"沧州","alias":"cangzhou","row_number":"30"},{"id":"223","name":"长春","alias":"changchun","row_number":"31"},{"id":"393","name":"常德","alias":"changde","row_number":"32"},{"id":"465","name":"长葛","alias":"changge","row_number":"33"},{"id":"429","name":"昌江县","alias":"changjiangxian","row_number":"34"},{"id":"308","name":"昌吉回族自治州","alias":"changjihuizuzizhizhou","row_number":"35"},{"id":"392","name":"长沙","alias":"changsha","row_number":"36"},{"id":"339","name":"常熟","alias":"changshu","row_number":"37"},{"id":"140","name":"长治","alias":"changzhi","row_number":"38"},{"id":"337","name":"常州","alias":"changzhou","row_number":"39"},{"id":"254","name":"巢湖","alias":"chaohu","row_number":"40"},{"id":"216","name":"朝阳","alias":"chaoyang","row_number":"41"},{"id":"360","name":"潮州","alias":"chaozhou","row_number":"42"},{"id":"70","name":"承德","alias":"chengde","row_number":"43"},{"id":"97","name":"成都","alias":"chengdu","row_number":"44"},{"id":"431","name":"澄迈县","alias":"chengmaixian","row_number":"45"},{"id":"464","name":"郴州","alias":"chenzhou","row_number":"46"},{"id":"177","name":"赤峰","alias":"chifeng","row_number":"47"},{"id":"414","name":"池州","alias":"chizhou","row_number":"48"},{"id":"160","name":"楚雄","alias":"chuxiong","row_number":"49"},{"id":"235","name":"滁州","alias":"chuzhou","row_number":"50"}],"D":[{"id":"447","name":"大东海","alias":"dadonghai","row_number":"51"},{"id":"59","name":"大理","alias":"dali","row_number":"52"},{"id":"152","name":"大理","alias":"dali","row_number":"53"},{"id":"194","name":"大连","alias":"dalian","row_number":"54"},{"id":"202","name":"丹东","alias":"dandong","row_number":"55"},{"id":"105","name":"达且","alias":"daqie","row_number":"56"},{"id":"280","name":"大庆","alias":"daqing","row_number":"57"},{"id":"135","name":"大同","alias":"datong","row_number":"58"},{"id":"293","name":"大兴安岭","alias":"daxinganling","row_number":"59"},{"id":"127","name":"德阳","alias":"deyang","row_number":"60"},{"id":"199","name":"德州","alias":"dezhou","row_number":"61"},{"id":"277","name":"定西市","alias":"dingxishi","row_number":"62"},{"id":"435","name":"迪庆藏族自治州","alias":"diqingcangzuzizhizhou","row_number":"63"},{"id":"363","name":"东莞","alias":"dong","row_number":"64"},{"id":"116","name":"东丽区","alias":"dongliqu","row_number":"65"},{"id":"145","name":"都匀","alias":"duyun","row_number":"66"}],"E":[{"id":"179","name":"鄂尔多斯","alias":"eerduosi","row_number":"67"},{"id":"487","name":"额尔古纳","alias":"eerguna","row_number":"68"}],"F":[{"id":"361","name":"佛山","alias":"foshan","row_number":"69"},{"id":"110","name":"涪陵","alias":"fuling","row_number":"70"},{"id":"197","name":"抚顺","alias":"fushun","row_number":"71"},{"id":"209","name":"阜新","alias":"fuxin","row_number":"72"},{"id":"227","name":"阜阳","alias":"fuyang","row_number":"73"},{"id":"257","name":"福州","alias":"fuzhou","row_number":"74"}],"G":[{"id":"288","name":"甘南藏族自治州","alias":"gannancangzuzizhizhou","row_number":"75"},{"id":"409","name":"赣州","alias":"ganzhou","row_number":"76"},{"id":"377","name":"甘孜","alias":"ganzi","row_number":"77"},{"id":"153","name":"个旧","alias":"gejiu","row_number":"78"},{"id":"258","name":"公主岭","alias":"gongzhuling","row_number":"79"},{"id":"128","name":"广元","alias":"guangyuan","row_number":"80"},{"id":"11","name":"广州","alias":"guangzhou","row_number":"81"},{"id":"60","name":"桂林","alias":"guilin","row_number":"82"},{"id":"141","name":"贵阳","alias":"guiyang","row_number":"83"},{"id":"248","name":"果洛","alias":"guoluo","row_number":"84"},{"id":"233","name":"固原市","alias":"guyuanshi","row_number":"85"}],"H":[{"id":"261","name":"哈尔滨","alias":"haerbin","row_number":"86"},{"id":"243","name":"海北","alias":"haibei","row_number":"87"},{"id":"240","name":"海东市","alias":"haidongshi","row_number":"88"},{"id":"8","name":"海口","alias":"haikou","row_number":"89"},{"id":"246","name":"海南州","alias":"hainanzhou","row_number":"90"},{"id":"445","name":"海棠湾","alias":"haitangwan","row_number":"91"},{"id":"251","name":"海西","alias":"haixi","row_number":"92"},{"id":"301","name":"哈密","alias":"hami","row_number":"93"},{"id":"90","name":"邯郸","alias":"handan","row_number":"94"},{"id":"294","name":"杭州","alias":"hangzhou","row_number":"95"},{"id":"330","name":"汉中","alias":"hanzhong","row_number":"96"},{"id":"111","name":"河北区","alias":"hebeiqu","row_number":"97"},{"id":"489","name":"鹤壁","alias":"hebi","row_number":"98"},{"id":"106","name":"河东区","alias":"hedongqu","row_number":"99"},{"id":"217","name":"合肥","alias":"hefei","row_number":"100"},{"id":"275","name":"鹤岗","alias":"hegang","row_number":"101"},{"id":"291","name":"黑河","alias":"heihe","row_number":"102"},{"id":"98","name":"衡水","alias":"hengshui","row_number":"103"},{"id":"463","name":"衡阳","alias":"hengyang","row_number":"104"},{"id":"306","name":"和田地","alias":"hetiandi","row_number":"105"},{"id":"108","name":"河西区","alias":"hexiqu","row_number":"106"},{"id":"370","name":"河源","alias":"heyuan","row_number":"107"},{"id":"210","name":"菏泽","alias":"heze","row_number":"108"},{"id":"346","name":"贺州","alias":"hezhou","row_number":"109"},{"id":"112","name":"红桥区","alias":"hongqiaoqu","row_number":"110"},{"id":"334","name":"淮安","alias":"huaian","row_number":"111"},{"id":"237","name":"淮北","alias":"huaibei","row_number":"112"},{"id":"396","name":"怀化","alias":"huaihua","row_number":"113"},{"id":"221","name":"淮南","alias":"huainan","row_number":"114"},{"id":"389","name":"黄冈","alias":"huanggang","row_number":"115"},{"id":"244","name":"黄南","alias":"huangnan","row_number":"116"},{"id":"231","name":"黄山","alias":"huangshan","row_number":"117"},{"id":"170","name":"呼和浩特","alias":"huhehaote","row_number":"118"},{"id":"362","name":"惠州","alias":"huizhou","row_number":"119"},{"id":"218","name":"葫芦岛","alias":"huludao","row_number":"120"},{"id":"180","name":"呼伦贝尔","alias":"hulunbeier","row_number":"121"},{"id":"113","name":"沪州","alias":"huzhou","row_number":"122"},{"id":"295","name":"湖州","alias":"huzhou","row_number":"123"}],"J":[{"id":"285","name":"佳木斯","alias":"jiamusi","row_number":"124"},{"id":"406","name":"吉安","alias":"jian","row_number":"125"},{"id":"371","name":"江门","alias":"jiangmen","row_number":"126"},{"id":"467","name":"焦作","alias":"jiaozuo","row_number":"127"},{"id":"297","name":"嘉兴","alias":"jiaxing","row_number":"128"},{"id":"263","name":"嘉峪关","alias":"jiayuguan","row_number":"129"},{"id":"225","name":"吉林","alias":"jilin","row_number":"130"},{"id":"255","name":"吉林省长白山保护开发区","alias":"jilinshengchangbaishanbaohukaifaqu","row_number":"131"},{"id":"193","name":"济南","alias":"jinan","row_number":"132"},{"id":"265","name":"金昌市","alias":"jinchangshi","row_number":"133"},{"id":"156","name":"晋城","alias":"jincheng","row_number":"134"},{"id":"454","name":"景德镇","alias":"jingdezhen","row_number":"135"},{"id":"130","name":"静海区","alias":"jinghaiqu","row_number":"136"},{"id":"390","name":"荆州","alias":"jingzhou","row_number":"137"},{"id":"312","name":"金华","alias":"jinhua","row_number":"138"},{"id":"205","name":"济宁","alias":"jining","row_number":"139"},{"id":"121","name":"津南区","alias":"jinnanqu","row_number":"140"},{"id":"163","name":"晋中","alias":"jinzhong","row_number":"141"},{"id":"204","name":"锦州","alias":"jinzhou","row_number":"142"},{"id":"410","name":"九江","alias":"jiujiang","row_number":"143"},{"id":"271","name":"酒泉市","alias":"jiuquanshi","row_number":"144"},{"id":"272","name":"鸡西","alias":"jixi","row_number":"145"},{"id":"133","name":"蓟县","alias":"jixian","row_number":"146"},{"id":"420","name":"济源","alias":"jiyuan","row_number":"147"}],"K":[{"id":"461","name":"开封","alias":"kaifeng","row_number":"148"},{"id":"146","name":"凯里","alias":"kaili","row_number":"149"},{"id":"304","name":"喀什地","alias":"kashidi","row_number":"150"},{"id":"298","name":"克拉玛依","alias":"kelamayi","row_number":"151"},{"id":"313","name":"克孜勒苏柯尔克孜自治州","alias":"kezilesukeerkezizizhizhou","row_number":"152"},{"id":"348","name":"昆明","alias":"kunming","row_number":"153"},{"id":"151","name":"昆明","alias":"kunming","row_number":"154"}],"L":[{"id":"96","name":"廊坊","alias":"langfang","row_number":"155"},{"id":"262","name":"兰州市","alias":"lanzhoushi","row_number":"156"},{"id":"139","name":"拉萨","alias":"lasa","row_number":"157"},{"id":"430","name":"乐东县","alias":"ledongxian","row_number":"158"},{"id":"119","name":"乐山","alias":"leshan","row_number":"159"},{"id":"376","name":"凉山","alias":"liangshan","row_number":"160"},{"id":"335","name":"连云港","alias":"lianyungang","row_number":"161"},{"id":"211","name":"辽阳","alias":"liaoyang","row_number":"162"},{"id":"234","name":"辽源","alias":"liaoyuan","row_number":"163"},{"id":"58","name":"丽江","alias":"lijiang","row_number":"164"},{"id":"166","name":"临汾","alias":"linfen","row_number":"165"},{"id":"466","name":"临高","alias":"lingao","row_number":"166"},{"id":"441","name":"陵水","alias":"lingshui","row_number":"167"},{"id":"286","name":"临夏回族自治州","alias":"linxiahuizuzizhizhou","row_number":"168"},{"id":"208","name":"临沂","alias":"linyi","row_number":"169"},{"id":"310","name":"丽水","alias":"lishui","row_number":"170"},{"id":"253","name":"六安","alias":"liuan","row_number":"171"},{"id":"149","name":"六盘水","alias":"liupanshui","row_number":"172"},{"id":"344","name":"柳州","alias":"liuzhou","row_number":"173"},{"id":"451","name":"龙华区","alias":"longhuaqu","row_number":"174"},{"id":"279","name":"陇南市","alias":"longnanshi","row_number":"175"},{"id":"276","name":"龙岩","alias":"longyan","row_number":"176"},{"id":"397","name":"娄底","alias":"loudi","row_number":"177"},{"id":"424","name":"洛阳","alias":"luoyang","row_number":"178"},{"id":"167","name":"吕梁","alias":"lvliang","row_number":"179"}],"M":[{"id":"222","name":"马鞍山","alias":"maanshan","row_number":"180"},{"id":"124","name":"马尔康","alias":"maerkang","row_number":"181"},{"id":"373","name":"茂名","alias":"maoming","row_number":"182"},{"id":"256","name":"梅河口","alias":"meihekou","row_number":"183"},{"id":"492","name":"美兰区","alias":"meilanqu","row_number":"184"},{"id":"378","name":"眉山","alias":"meishan","row_number":"185"},{"id":"372","name":"梅州","alias":"meizhou","row_number":"186"},{"id":"103","name":"绵阳","alias":"mianyang","row_number":"187"},{"id":"290","name":"牡丹江","alias":"mudanjiang","row_number":"188"}],"N":[{"id":"403","name":"南昌","alias":"nanchang","row_number":"189"},{"id":"379","name":"南充","alias":"nanchong","row_number":"190"},{"id":"317","name":"南京","alias":"nanjing","row_number":"191"},{"id":"109","name":"南开区","alias":"nankaiqu","row_number":"192"},{"id":"61","name":"南宁","alias":"nanning","row_number":"193"},{"id":"289","name":"南平","alias":"nanping","row_number":"194"},{"id":"449","name":"南山区","alias":"nanshanqu","row_number":"195"},{"id":"327","name":"南通","alias":"nantong","row_number":"196"},{"id":"462","name":"南阳","alias":"nanyang","row_number":"197"},{"id":"117","name":"内江","alias":"neijiang","row_number":"198"},{"id":"300","name":"宁波","alias":"ningbo","row_number":"199"},{"id":"260","name":"宁德","alias":"ningde","row_number":"200"},{"id":"129","name":"宁河区","alias":"ninghequ","row_number":"201"}],"P":[{"id":"213","name":"盘锦","alias":"panjin","row_number":"202"},{"id":"99","name":"攀枝花","alias":"panzhihua","row_number":"203"},{"id":"498","name":"平顶山","alias":"pingdingshan","row_number":"204"},{"id":"281","name":"平凉市","alias":"pingliangshi","row_number":"205"},{"id":"407","name":"萍乡","alias":"pingxiang","row_number":"206"},{"id":"352","name":"普洱","alias":"puer","row_number":"207"},{"id":"264","name":"莆田","alias":"putian","row_number":"208"}],"Q":[{"id":"411","name":"黔东南","alias":"qiandongnan","row_number":"209"},{"id":"413","name":"黔南","alias":"qiannan","row_number":"210"},{"id":"412","name":"黔西南","alias":"qianxinan","row_number":"211"},{"id":"196","name":"青岛","alias":"qingdao","row_number":"212"},{"id":"283","name":"庆阳市","alias":"qingyangshi","row_number":"213"},{"id":"369","name":"清远","alias":"qingyuan","row_number":"214"},{"id":"88","name":"秦皇岛","alias":"qinhuangdao","row_number":"215"},{"id":"381","name":"钦州","alias":"qinzhou","row_number":"216"},{"id":"174","name":"琼海","alias":"qionghaishi","row_number":"217"},{"id":"268","name":"齐齐哈尔","alias":"qiqihaer","row_number":"218"},{"id":"287","name":"七台河","alias":"qitaihe","row_number":"219"},{"id":"267","name":"泉州","alias":"quanzhou","row_number":"220"},{"id":"354","name":"曲靖","alias":"qujing","row_number":"221"},{"id":"154","name":"曲靖","alias":"qujing","row_number":"222"}],"R":[{"id":"214","name":"日照","alias":"rizhao","row_number":"223"}],"S":[{"id":"432","name":"三门峡","alias":"sanmenxia","row_number":"224"},{"id":"284","name":"三明","alias":"sanming","row_number":"225"},{"id":"9","name":"三亚","alias":"sanya","row_number":"226"},{"id":"450","name":"三亚市区","alias":"sanyashiqu","row_number":"227"},{"id":"448","name":"三亚湾","alias":"sanyawan","row_number":"228"},{"id":"331","name":"商洛","alias":"shangluo","row_number":"229"},{"id":"404","name":"上饶","alias":"shangrao","row_number":"230"},{"id":"359","name":"汕头","alias":"shantou","row_number":"231"},{"id":"368","name":"韶关","alias":"shaoguan","row_number":"232"},{"id":"303","name":"绍兴","alias":"shaoxing","row_number":"233"},{"id":"12","name":"深圳","alias":"shen","row_number":"234"},{"id":"387","name":"神农架","alias":"shennongjia","row_number":"235"},{"id":"192","name":"沈阳","alias":"shenyang","row_number":"236"},{"id":"69","name":"石家庄","alias":"shijiazhuang","row_number":"237"},{"id":"386","name":"十堰","alias":"shiyan","row_number":"238"},{"id":"230","name":"石嘴山市","alias":"shizuishanshi","row_number":"239"},{"id":"278","name":"双鸭山","alias":"shuangyashan","row_number":"240"},{"id":"158","name":"朔州","alias":"shuozhou","row_number":"241"},{"id":"422","name":"四会","alias":"sihui","row_number":"242"},{"id":"161","name":"思茅","alias":"simao","row_number":"243"},{"id":"229","name":"四平","alias":"siping","row_number":"244"},{"id":"247","name":"松原","alias":"songyuan","row_number":"245"},{"id":"292","name":"绥化","alias":"suihua","row_number":"246"},{"id":"357","name":"宿迁","alias":"suqian","row_number":"247"},{"id":"323","name":"苏州","alias":"suzhou","row_number":"248"},{"id":"226","name":"宿州","alias":"suzhou","row_number":"249"}],"T":[{"id":"207","name":"泰安","alias":"taian","row_number":"250"},{"id":"134","name":"太原","alias":"taiyuan","row_number":"251"},{"id":"340","name":"泰州","alias":"taizhou","row_number":"252"},{"id":"305","name":"台州","alias":"taizhou","row_number":"253"},{"id":"87","name":"唐山","alias":"tangshan","row_number":"254"},{"id":"351","name":"腾冲","alias":"tengchong","row_number":"255"},{"id":"269","name":"天水市","alias":"tianshuishi","row_number":"256"},{"id":"215","name":"铁岭","alias":"tieling","row_number":"257"},{"id":"324","name":"铜川","alias":"tongchuan","row_number":"258"},{"id":"238","name":"通化","alias":"tonghua","row_number":"259"},{"id":"178","name":"通辽","alias":"tongliao","row_number":"260"},{"id":"241","name":"铜陵","alias":"tongling","row_number":"261"},{"id":"147","name":"铜仁","alias":"tongren","row_number":"262"},{"id":"299","name":"吐鲁番","alias":"tulufan","row_number":"263"}],"W":[{"id":"10","name":"万宁","alias":"wanning","row_number":"264"},{"id":"107","name":"万县市","alias":"wanxianshi","row_number":"265"},{"id":"203","name":"潍坊","alias":"weifang","row_number":"266"},{"id":"212","name":"威海","alias":"weihai","row_number":"267"},{"id":"322","name":"渭南","alias":"weinan","row_number":"268"},{"id":"173","name":"文昌","alias":"wenchangshi","row_number":"269"},{"id":"102","name":"温江","alias":"wenjiang","row_number":"270"},{"id":"157","name":"文山","alias":"wenshan","row_number":"271"},{"id":"374","name":"文山","alias":"wenshan","row_number":"272"},{"id":"307","name":"温州","alias":"wenzhou","row_number":"273"},{"id":"175","name":"乌海","alias":"wuhai","row_number":"274"},{"id":"383","name":"武汉","alias":"wuhan","row_number":"275"},{"id":"220","name":"芜湖","alias":"wuhu","row_number":"276"},{"id":"182","name":"乌兰察布","alias":"wulanchabu","row_number":"277"},{"id":"296","name":"乌鲁木齐","alias":"wulumuqi","row_number":"278"},{"id":"125","name":"武清区","alias":"wuqingqu","row_number":"279"},{"id":"274","name":"武威市","alias":"wuweishi","row_number":"280"},{"id":"338","name":"无锡","alias":"wuxi","row_number":"281"},{"id":"171","name":"五指山市","alias":"wuzhishanshi","row_number":"282"},{"id":"232","name":"吴忠市","alias":"wuzhongshi","row_number":"283"},{"id":"347","name":"梧州","alias":"wuzhou","row_number":"284"}],"X":[{"id":"259","name":"厦门","alias":"xiamen","row_number":"285"},{"id":"318","name":"西安","alias":"xian","row_number":"286"},{"id":"350","name":"香格里拉","alias":"xianggelila","row_number":"287"},{"id":"395","name":"湘潭","alias":"xiangtan","row_number":"288"},{"id":"400","name":"湘西","alias":"xiangxi","row_number":"289"},{"id":"388","name":"襄阳","alias":"xiangyang","row_number":"290"},{"id":"321","name":"咸阳","alias":"xianyang","row_number":"291"},{"id":"385","name":"孝感","alias":"xiaogan","row_number":"292"},{"id":"120","name":"西昌","alias":"xichang","row_number":"293"},{"id":"184","name":"兴安盟","alias":"xinganmeng","row_number":"294"},{"id":"91","name":"邢台","alias":"xingtai","row_number":"295"},{"id":"150","name":"兴义","alias":"xingyi","row_number":"296"},{"id":"239","name":"西宁市","alias":"xiningshi","row_number":"297"},{"id":"421","name":"新乡","alias":"xinxiang","row_number":"298"},{"id":"423","name":"信阳","alias":"xinyang","row_number":"299"},{"id":"165","name":"忻州","alias":"xinzhou","row_number":"300"},{"id":"118","name":"西青区","alias":"xiqingqu","row_number":"301"},{"id":"66","name":"西双版纳","alias":"xishuangbanna","row_number":"302"},{"id":"452","name":"秀英区","alias":"xiuyingqu","row_number":"303"},{"id":"249","name":"宣城","alias":"xuancheng","row_number":"304"},{"id":"469","name":"许昌","alias":"xuchang","row_number":"305"},{"id":"333","name":"徐州","alias":"xuzhou","row_number":"306"}],"Y":[{"id":"123","name":"雅安","alias":"yaan","row_number":"307"},{"id":"446","name":"亚龙湾","alias":"yalongwan","row_number":"308"},{"id":"325","name":"延安","alias":"yanan","row_number":"309"},{"id":"252","name":"延边朝鲜族自治州","alias":"yanbianchaoxianzuzizhizhou","row_number":"310"},{"id":"332","name":"盐城","alias":"yancheng","row_number":"311"},{"id":"366","name":"阳江","alias":"yangjiang","row_number":"312"},{"id":"138","name":"阳泉","alias":"yangquan","row_number":"313"},{"id":"329","name":"扬州","alias":"yangzhou","row_number":"314"},{"id":"201","name":"烟台","alias":"yantai","row_number":"315"},{"id":"115","name":"宜宾","alias":"yibin","row_number":"316"},{"id":"384","name":"宜昌","alias":"yichang","row_number":"317"},{"id":"282","name":"伊春","alias":"yichun","row_number":"318"},{"id":"408","name":"宜春","alias":"yichun","row_number":"319"},{"id":"314","name":"伊犁哈萨克自治州（副省级自治州）","alias":"yilihasakezizhizhoufushengjizizhizhou","row_number":"320"},{"id":"228","name":"银川市","alias":"yinchuanshi","row_number":"321"},{"id":"206","name":"营口","alias":"yingkou","row_number":"322"},{"id":"405","name":"鹰潭","alias":"yingtan","row_number":"323"},{"id":"398","name":"益阳","alias":"yiyang","row_number":"324"},{"id":"101","name":"永川","alias":"yongchuan","row_number":"325"},{"id":"399","name":"岳阳","alias":"yueyang","row_number":"326"},{"id":"326","name":"榆林","alias":"yulin","row_number":"327"},{"id":"345","name":"玉林","alias":"yulin","row_number":"328"},{"id":"164","name":"运城","alias":"yuncheng","row_number":"329"},{"id":"250","name":"玉树","alias":"yushu","row_number":"330"},{"id":"353","name":"玉溪","alias":"yuxi","row_number":"331"},{"id":"159","name":"玉溪","alias":"yuxi","row_number":"332"}],"Z":[{"id":"455","name":"枣庄","alias":"zaozhuang","row_number":"333"},{"id":"394","name":"张家界","alias":"zhangjiajie","row_number":"334"},{"id":"93","name":"张家口","alias":"zhangjiakou","row_number":"335"},{"id":"273","name":"张掖市","alias":"zhangyeshi","row_number":"336"},{"id":"270","name":"漳州","alias":"zhangzhou","row_number":"337"},{"id":"367","name":"湛江","alias":"zhanjiang","row_number":"338"},{"id":"365","name":"肇庆","alias":"zhaoqing","row_number":"339"},{"id":"375","name":"昭通","alias":"zhaotong","row_number":"340"},{"id":"162","name":"昭通","alias":"zhaotong","row_number":"341"},{"id":"434","name":"郑州","alias":"zhengzhou","row_number":"342"},{"id":"320","name":"镇江","alias":"zhenjiang","row_number":"343"},{"id":"364","name":"中山","alias":"zhongshan","row_number":"344"},{"id":"236","name":"中卫市","alias":"zhongweishi","row_number":"345"},{"id":"315","name":"衢州","alias":"zhou","row_number":"346"},{"id":"316","name":"舟山","alias":"zhoushan","row_number":"347"},{"id":"169","name":"儋州市","alias":"zhoushi","row_number":"348"},{"id":"358","name":"珠海","alias":"zhuhai","row_number":"349"},{"id":"453","name":"株洲","alias":"zhuzhou","row_number":"350"},{"id":"198","name":"淄博","alias":"zibo","row_number":"351"},{"id":"100","name":"自贡","alias":"zigong","row_number":"352"},{"id":"143","name":"遵义","alias":"zunyi","row_number":"353"}]}
     */

    private DataBean data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private OtherBean other;
        /**
         * id : 8
         * name : 海口
         * row_number : 1
         */

        private ArrayList<HotBean> hot;

        public OtherBean getOther() {
            return other;
        }

        public void setOther(OtherBean other) {
            this.other = other;
        }

        public ArrayList<HotBean> getHot() {
            return hot;
        }

        public void setHot(ArrayList<HotBean> hot) {
            this.hot = hot;
        }

        public static class OtherBean {
            /**
             * id : 355
             * name : 阿坝
             * alias : aba
             * row_number : 1
             */

            private List<ABean> A;
            /**
             * id : 245
             * name : 白城
             * alias : baicheng
             * row_number : 9
             */

            private List<BBean> B;
            /**
             * id : 94
             * name : 沧州
             * alias : cangzhou
             * row_number : 30
             */

            private List<CBean> C;
            /**
             * id : 447
             * name : 大东海
             * alias : dadonghai
             * row_number : 51
             */

            private List<DBean> D;
            /**
             * id : 179
             * name : 鄂尔多斯
             * alias : eerduosi
             * row_number : 67
             */

            private List<EBean> E;
            /**
             * id : 361
             * name : 佛山
             * alias : foshan
             * row_number : 69
             */

            private List<FBean> F;
            /**
             * id : 288
             * name : 甘南藏族自治州
             * alias : gannancangzuzizhizhou
             * row_number : 75
             */

            private List<GBean> G;
            /**
             * id : 261
             * name : 哈尔滨
             * alias : haerbin
             * row_number : 86
             */

            private List<HBean> H;
            /**
             * id : 285
             * name : 佳木斯
             * alias : jiamusi
             * row_number : 124
             */

            private List<JBean> J;
            /**
             * id : 461
             * name : 开封
             * alias : kaifeng
             * row_number : 148
             */

            private List<KBean> K;
            /**
             * id : 96
             * name : 廊坊
             * alias : langfang
             * row_number : 155
             */

            private List<LBean> L;
            /**
             * id : 222
             * name : 马鞍山
             * alias : maanshan
             * row_number : 180
             */

            private List<MBean> M;
            /**
             * id : 403
             * name : 南昌
             * alias : nanchang
             * row_number : 189
             */

            private List<NBean> N;
            /**
             * id : 213
             * name : 盘锦
             * alias : panjin
             * row_number : 202
             */

            private List<PBean> P;
            /**
             * id : 411
             * name : 黔东南
             * alias : qiandongnan
             * row_number : 209
             */

            private List<QBean> Q;
            /**
             * id : 214
             * name : 日照
             * alias : rizhao
             * row_number : 223
             */

            private List<RBean> R;
            /**
             * id : 432
             * name : 三门峡
             * alias : sanmenxia
             * row_number : 224
             */

            private List<SBean> S;
            /**
             * id : 207
             * name : 泰安
             * alias : taian
             * row_number : 250
             */

            private List<TBean> T;
            /**
             * id : 10
             * name : 万宁
             * alias : wanning
             * row_number : 264
             */

            private List<WBean> W;
            /**
             * id : 259
             * name : 厦门
             * alias : xiamen
             * row_number : 285
             */

            private List<XBean> X;
            /**
             * id : 123
             * name : 雅安
             * alias : yaan
             * row_number : 307
             */

            private List<YBean> Y;
            /**
             * id : 455
             * name : 枣庄
             * alias : zaozhuang
             * row_number : 333
             */

            private List<ZBean> Z;

            public List<ABean> getA() {
                return A;
            }

            public void setA(List<ABean> A) {
                this.A = A;
            }

            public List<BBean> getB() {
                return B;
            }

            public void setB(List<BBean> B) {
                this.B = B;
            }

            public List<CBean> getC() {
                return C;
            }

            public void setC(List<CBean> C) {
                this.C = C;
            }

            public List<DBean> getD() {
                return D;
            }

            public void setD(List<DBean> D) {
                this.D = D;
            }

            public List<EBean> getE() {
                return E;
            }

            public void setE(List<EBean> E) {
                this.E = E;
            }

            public List<FBean> getF() {
                return F;
            }

            public void setF(List<FBean> F) {
                this.F = F;
            }

            public List<GBean> getG() {
                return G;
            }

            public void setG(List<GBean> G) {
                this.G = G;
            }

            public List<HBean> getH() {
                return H;
            }

            public void setH(List<HBean> H) {
                this.H = H;
            }

            public List<JBean> getJ() {
                return J;
            }

            public void setJ(List<JBean> J) {
                this.J = J;
            }

            public List<KBean> getK() {
                return K;
            }

            public void setK(List<KBean> K) {
                this.K = K;
            }

            public List<LBean> getL() {
                return L;
            }

            public void setL(List<LBean> L) {
                this.L = L;
            }

            public List<MBean> getM() {
                return M;
            }

            public void setM(List<MBean> M) {
                this.M = M;
            }

            public List<NBean> getN() {
                return N;
            }

            public void setN(List<NBean> N) {
                this.N = N;
            }

            public List<PBean> getP() {
                return P;
            }

            public void setP(List<PBean> P) {
                this.P = P;
            }

            public List<QBean> getQ() {
                return Q;
            }

            public void setQ(List<QBean> Q) {
                this.Q = Q;
            }

            public List<RBean> getR() {
                return R;
            }

            public void setR(List<RBean> R) {
                this.R = R;
            }

            public List<SBean> getS() {
                return S;
            }

            public void setS(List<SBean> S) {
                this.S = S;
            }

            public List<TBean> getT() {
                return T;
            }

            public void setT(List<TBean> T) {
                this.T = T;
            }

            public List<WBean> getW() {
                return W;
            }

            public void setW(List<WBean> W) {
                this.W = W;
            }

            public List<XBean> getX() {
                return X;
            }

            public void setX(List<XBean> X) {
                this.X = X;
            }

            public List<YBean> getY() {
                return Y;
            }

            public void setY(List<YBean> Y) {
                this.Y = Y;
            }

            public List<ZBean> getZ() {
                return Z;
            }

            public void setZ(List<ZBean> Z) {
                this.Z = Z;
            }

            public static class ABean {
                private String id;
                private String name;
                private String alias;
                private String row_number;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getAlias() {
                    return alias;
                }

                public void setAlias(String alias) {
                    this.alias = alias;
                }

                public String getRow_number() {
                    return row_number;
                }

                public void setRow_number(String row_number) {
                    this.row_number = row_number;
                }
            }

            public static class BBean {
                private String id;
                private String name;
                private String alias;
                private String row_number;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getAlias() {
                    return alias;
                }

                public void setAlias(String alias) {
                    this.alias = alias;
                }

                public String getRow_number() {
                    return row_number;
                }

                public void setRow_number(String row_number) {
                    this.row_number = row_number;
                }
            }

            public static class CBean {
                private String id;
                private String name;
                private String alias;
                private String row_number;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getAlias() {
                    return alias;
                }

                public void setAlias(String alias) {
                    this.alias = alias;
                }

                public String getRow_number() {
                    return row_number;
                }

                public void setRow_number(String row_number) {
                    this.row_number = row_number;
                }
            }

            public static class DBean {
                private String id;
                private String name;
                private String alias;
                private String row_number;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getAlias() {
                    return alias;
                }

                public void setAlias(String alias) {
                    this.alias = alias;
                }

                public String getRow_number() {
                    return row_number;
                }

                public void setRow_number(String row_number) {
                    this.row_number = row_number;
                }
            }

            public static class EBean {
                private String id;
                private String name;
                private String alias;
                private String row_number;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getAlias() {
                    return alias;
                }

                public void setAlias(String alias) {
                    this.alias = alias;
                }

                public String getRow_number() {
                    return row_number;
                }

                public void setRow_number(String row_number) {
                    this.row_number = row_number;
                }
            }

            public static class FBean {
                private String id;
                private String name;
                private String alias;
                private String row_number;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getAlias() {
                    return alias;
                }

                public void setAlias(String alias) {
                    this.alias = alias;
                }

                public String getRow_number() {
                    return row_number;
                }

                public void setRow_number(String row_number) {
                    this.row_number = row_number;
                }
            }

            public static class GBean {
                private String id;
                private String name;
                private String alias;
                private String row_number;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getAlias() {
                    return alias;
                }

                public void setAlias(String alias) {
                    this.alias = alias;
                }

                public String getRow_number() {
                    return row_number;
                }

                public void setRow_number(String row_number) {
                    this.row_number = row_number;
                }
            }

            public static class HBean {
                private String id;
                private String name;
                private String alias;
                private String row_number;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getAlias() {
                    return alias;
                }

                public void setAlias(String alias) {
                    this.alias = alias;
                }

                public String getRow_number() {
                    return row_number;
                }

                public void setRow_number(String row_number) {
                    this.row_number = row_number;
                }
            }

            public static class JBean {
                private String id;
                private String name;
                private String alias;
                private String row_number;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getAlias() {
                    return alias;
                }

                public void setAlias(String alias) {
                    this.alias = alias;
                }

                public String getRow_number() {
                    return row_number;
                }

                public void setRow_number(String row_number) {
                    this.row_number = row_number;
                }
            }

            public static class KBean {
                private String id;
                private String name;
                private String alias;
                private String row_number;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getAlias() {
                    return alias;
                }

                public void setAlias(String alias) {
                    this.alias = alias;
                }

                public String getRow_number() {
                    return row_number;
                }

                public void setRow_number(String row_number) {
                    this.row_number = row_number;
                }
            }

            public static class LBean {
                private String id;
                private String name;
                private String alias;
                private String row_number;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getAlias() {
                    return alias;
                }

                public void setAlias(String alias) {
                    this.alias = alias;
                }

                public String getRow_number() {
                    return row_number;
                }

                public void setRow_number(String row_number) {
                    this.row_number = row_number;
                }
            }

            public static class MBean {
                private String id;
                private String name;
                private String alias;
                private String row_number;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getAlias() {
                    return alias;
                }

                public void setAlias(String alias) {
                    this.alias = alias;
                }

                public String getRow_number() {
                    return row_number;
                }

                public void setRow_number(String row_number) {
                    this.row_number = row_number;
                }
            }

            public static class NBean {
                private String id;
                private String name;
                private String alias;
                private String row_number;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getAlias() {
                    return alias;
                }

                public void setAlias(String alias) {
                    this.alias = alias;
                }

                public String getRow_number() {
                    return row_number;
                }

                public void setRow_number(String row_number) {
                    this.row_number = row_number;
                }
            }

            public static class PBean {
                private String id;
                private String name;
                private String alias;
                private String row_number;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getAlias() {
                    return alias;
                }

                public void setAlias(String alias) {
                    this.alias = alias;
                }

                public String getRow_number() {
                    return row_number;
                }

                public void setRow_number(String row_number) {
                    this.row_number = row_number;
                }
            }

            public static class QBean {
                private String id;
                private String name;
                private String alias;
                private String row_number;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getAlias() {
                    return alias;
                }

                public void setAlias(String alias) {
                    this.alias = alias;
                }

                public String getRow_number() {
                    return row_number;
                }

                public void setRow_number(String row_number) {
                    this.row_number = row_number;
                }
            }

            public static class RBean {
                private String id;
                private String name;
                private String alias;
                private String row_number;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getAlias() {
                    return alias;
                }

                public void setAlias(String alias) {
                    this.alias = alias;
                }

                public String getRow_number() {
                    return row_number;
                }

                public void setRow_number(String row_number) {
                    this.row_number = row_number;
                }
            }

            public static class SBean {
                private String id;
                private String name;
                private String alias;
                private String row_number;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getAlias() {
                    return alias;
                }

                public void setAlias(String alias) {
                    this.alias = alias;
                }

                public String getRow_number() {
                    return row_number;
                }

                public void setRow_number(String row_number) {
                    this.row_number = row_number;
                }
            }

            public static class TBean {
                private String id;
                private String name;
                private String alias;
                private String row_number;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getAlias() {
                    return alias;
                }

                public void setAlias(String alias) {
                    this.alias = alias;
                }

                public String getRow_number() {
                    return row_number;
                }

                public void setRow_number(String row_number) {
                    this.row_number = row_number;
                }
            }

            public static class WBean {
                private String id;
                private String name;
                private String alias;
                private String row_number;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getAlias() {
                    return alias;
                }

                public void setAlias(String alias) {
                    this.alias = alias;
                }

                public String getRow_number() {
                    return row_number;
                }

                public void setRow_number(String row_number) {
                    this.row_number = row_number;
                }
            }

            public static class XBean {
                private String id;
                private String name;
                private String alias;
                private String row_number;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getAlias() {
                    return alias;
                }

                public void setAlias(String alias) {
                    this.alias = alias;
                }

                public String getRow_number() {
                    return row_number;
                }

                public void setRow_number(String row_number) {
                    this.row_number = row_number;
                }
            }

            public static class YBean {
                private String id;
                private String name;
                private String alias;
                private String row_number;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getAlias() {
                    return alias;
                }

                public void setAlias(String alias) {
                    this.alias = alias;
                }

                public String getRow_number() {
                    return row_number;
                }

                public void setRow_number(String row_number) {
                    this.row_number = row_number;
                }
            }

            public static class ZBean {
                private String id;
                private String name;
                private String alias;
                private String row_number;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getAlias() {
                    return alias;
                }

                public void setAlias(String alias) {
                    this.alias = alias;
                }

                public String getRow_number() {
                    return row_number;
                }

                public void setRow_number(String row_number) {
                    this.row_number = row_number;
                }
            }
        }

        public static class HotBean {
            private String id;
            private String name;
            private String row_number;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getRow_number() {
                return row_number;
            }

            public void setRow_number(String row_number) {
                this.row_number = row_number;
            }
        }
    }
}
