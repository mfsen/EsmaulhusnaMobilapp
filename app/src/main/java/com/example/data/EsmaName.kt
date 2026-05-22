package com.example.data

enum class EsmaCategory(val displayName: String) {
    RAHMET("Rahmet & Şefkat"),
    KUDRET("Kudret & Azamet"),
    ILIM("İlim & Hüküm"),
    YARATILIS("Yaratılış & Nimet"),
    TENZIH("Arınma & Kusursuzluk")
}

data class EsmaName(
    val id: Int,
    val name: String,
    val arabic: String,
    val pronunciation: String,
    val meaning: String,
    val description: String,
    val category: EsmaCategory
)

object EsmaDataProvider {
    val ALL_NAMES = listOf(
        EsmaName(
            1, "Allah", "الله", "Allah",
            "Eşi benzeri olmayan, tek ilah, her şeyin yaratıcısı ve maliki olan mutlak varlık.",
            "Tüm esma ve sıfatları kendisinde toplayan en yüce isim. Kalpler ancak O'nu anarak huzur bulur. Tefekkür ederken O'nun mutlak birliğini ve varlığımızın kaynağı olduğunu hatırlarız.",
            EsmaCategory.TENZIH
        ),
        EsmaName(
            2, "Er-Rahmân", "الرَّحْمَنُ", "er-Rahmân",
            "Dünya hayatında zerre kadar ayırt etmeksizin tüm mahlukata şefkat ve merhamet eden.",
            "Kafir, mümin, karınca, ağaç ayırt etmeden her yaratılana rızık veren, merhamet kollarını açan sonsuz şefkat sahibi. O'nun rahmeti gazabını geçmiştir. Rahmetle dolmalı, yaratılanı yaratandan ötürü sevmeliyiz.",
            EsmaCategory.RAHMET
        ),
        EsmaName(
            3, "Er-Rahîm", "الرَّحِيمُ", "er-Rahîm",
            "Ahirette sadece mümin kullarına özel ve daimi şekilde lütufta bulunup acıyan.",
            "İman edenlere karşı hususi bir lütuf, bağışlama ve selamet ulaştıran. Adaletinin içinde sevginin ve kurtuluşun adıdır. Bizler de hayatımızda dürüst ve vefakar bir merhamet anlayışı geliştirmeliyiz.",
            EsmaCategory.RAHMET
        ),
        EsmaName(
            4, "El-Melik", "الْمَلِكُ", "el-Melik",
            "Bütün kainatın, her varlığın hakiki hükümdarı, yöneticisi ve mutlak sahibi.",
            "O'ndan başka egemenlik sahibi yoktur. Dünyadaki tüm geçici makam, mülk ve güçler O'nun izniyledir ve fanidir. Bu ismi düşünmek, dünyevi hırsları dindirir, insanı kula kul olmaktan kurtarır.",
            EsmaCategory.KUDRET
        ),
        EsmaName(
            5, "El-Kuddûs", "الْقُدُّوسُ", "el-Kuddûs",
            "Her türlü hatadan, eksiklikten münezzeh, temiz ve mukaddes olan, her şeyi temizleyen.",
            "Zatı, sıfatları ve fiilleri mükemmel olandır. O, yarattığı kalpleri, ruhları ve dünyayı da arındırandır. Fiziksel ve ruhani pisliklerden arınarak O'nun rızasına yol almalıyız.",
            EsmaCategory.TENZIH
        ),
        EsmaName(
            6, "Es-Selâm", "السَّلَامُ", "es-Selâm",
            "Kullarına esenlik, barış ve selamet veren; her türlü tehlikeden kurtaran.",
            "Cennetin, huzurun ve emniyetin kaynağıdır. İnsanlara selamı yaymak, barışçıl ve adil olmak bu ismin ahlakıyla bezenmeyi gerektirir. Kalbimizdeki fırtınaları sakinleştirecek tek sığınaktır.",
            EsmaCategory.RAHMET
        ),
        EsmaName(
            7, "El-Mü'min", "الْمُؤْمِنُ", "el-Mü'min",
            "Kalplerde iman nurunu uyandıran, kullarına güven ve emniyet veren.",
            "Güven hissinin yaratıcısıdır. O'na sığınan asla korkuya kapılmaz. Bizler de etrafımıza güven vermeli, ahdimize sadık kalmalıyız ki bu yüce ismin bir yansıması olalım.",
            EsmaCategory.RAHMET
        ),
        EsmaName(
            8, "El-Müheymin", "الْمُهَيْمِنُ", "el-Müheymin",
            "Her şeyi gözetip kollayan, koruyucu kanatları altında tutan, güvenliği sağlayan.",
            "Sonsuz merhametiyle her kulunu her an izleyen, gizli ve aşikarı bilen, emniyet çemberiyle kuşatandır. O'nun gözetimi altında olduğumuz bilinciyle hareket etmek zarafet getirir.",
            EsmaCategory.KUDRET
        ),
        EsmaName(
            9, "El-Azîz", "الْعَزِيزُ", "el-Azîz",
            "Mağlup edilmesi imkansız olan, izzet ve şerefi mutlak olan, her şeye galip gelen.",
            "Bütün izzet O'na aittir. O'nun yanında olan aziz, uzaklaşan ise rezil olur. İzzet ve onuru dünyevi makamlarda değil, Allah'ın rızasında aramayı tefekkür etmeliyiz.",
            EsmaCategory.KUDRET
        ),
        EsmaName(
            10, "El-Cebbâr", "الْجَبَّارُ", "el-Cebbâr",
            "Eksikleri tamamlayan, kırıkları saran, dilediğini zorla da olsa yaptırmaya muktedir olan.",
            "Ezilenlerin feryadına yetişip onları düze çıkaran, kalbi kırık olanları ilahi tesellisiyle iyileştiren. Kendi irademizi O'nun rızasına teslim ettiğimizde en büyük içsel güce kavuşuruz.",
            EsmaCategory.KUDRET
        ),
        EsmaName(
            11, "El-Mütekebbir", "الْمُتَكَبِّرُ", "el-Mütekebbir",
            "Sonsuz büyüklük ve azamet sahibi, kibriya hakkı yalnızca kendisine ait olan.",
            "Büyüklükte, kudrette ortağı olmayandır. Kibir ancak O'na layıktır. İnsanın kendi acziyetini bilmesi ve alçakgönüllü olması bu ismin karşısındaki hakiki edebidir.",
            EsmaCategory.TENZIH
        ),
        EsmaName(
            12, "El-Hâlık", "الْخَالِقُ", "el-Hâlık",
            "Her şeyi yoktan var eden, yaratma işini ölçülü ve kusursuzca gerçekleştiren.",
            "Kainattaki her bir atomun hikmetli nizama göre tasarlandığını görmek bu ismi tefekkür etmektir. Hiçbir şey tesadüfi değildir; her zerre O'nun yaratışının mucizesidir.",
            EsmaCategory.YARATILIS
        ),
        EsmaName(
            13, "El-Bâri", "الْبَارِئُ", "el-Bâri",
            "Yarattığı her şeyi birbirine uyumlu, kusursuz ve örneksiz meydana getiren.",
            "Fizik kanunları, ekosistemin kusursuz dengesi ve hücrelerin uyumu el-Bari isminin tecellisidir. O, yarattığı hiçbir şeyi birbirine karıştırmadan nizam içinde yaşatır.",
            EsmaCategory.YARATILIS
        ),
        EsmaName(
            14, "El-Musavvir", "الْمُصَوِّرُ", "el-Musavvir",
            "Yarattıklarına biçim, renk, suret ve özellikler veren sanatkâr.",
            "Parmak uçlarımızdaki çizgilerden göz renklerimize kadar her canlıya özgün bir tasarım bahşedendir. Bu ismi tefekkür etmek estetik ve sanatsal bir hayranlık doğurur.",
            EsmaCategory.YARATILIS
        ),
        EsmaName(
            15, "El-Gaffâr", "الْغَفَّارُ", "el-Gaffâr",
            "Kullarının günahlarını tekrar tekrar örten, çok affeden ve mağfiret eden.",
            "Günahlarımız ne kadar çok ve büyük olursa olsun, O'nun affediciliği daha büyüktür. Bu isim ümit kapısıdır. Bizler de başkalarının hatalarını örtmeli ve affedici olmalıyız.",
            EsmaCategory.RAHMET
        ),
        EsmaName(
            16, "El-Kahhâr", "الْقَهَّارُ", "el-Kahhâr",
            "Her şeye mutlak galip gelen, düşmanlarını ve isyankarları perişan etmeye muktedir.",
            "Karanlığı, zulmü ve zalimleri adaletle ezen güçtür. Hakiki güç sahibinin yalnızca Allah olduğunu bilmek, kalbe heybet ve doğruluk yerleştirir.",
            EsmaCategory.KUDRET
        ),
        EsmaName(
            17, "El-Vehhâb", "الْوَهَّابُ", "el-Vehhâb",
            "Karşılık beklemeden, hiçbir şarta bağlamadan bol bol ihsan ve bağışta bulunan.",
            "Karşılıksız vermenin zirvesidir. Hak etmediğimiz halde aldığımız nefes, sağlık ve hidayet O'nun birer hediyesidir. Teşekkür ve minnetle dolmamızı sağlar.",
            EsmaCategory.RAHMET
        ),
        EsmaName(
            18, "Er-Rezzâk", "الرَّزَّاقُ", "er-Rezzâk",
            "Bütün canlıların rızkını veren, bedenlerin ve ruhların gıdasını yaratan.",
            "Sadece midenin rızkını değil; zihnin, kalbin ve ruhun rızkını da verendir. Rızık endişesi yaşamadan O'na tevekkül etmek, helal dairesinde çalışıp kanaatkar olmaktır.",
            EsmaCategory.YARATILIS
        ),
        EsmaName(
            19, "El-Fettâh", "الْفَتَّاحُ", "el-Fettâh",
            "Zorlukları kolaylaştıran, darda kalanlara kapılar açan, mutlak adaleti tecelli ettiren.",
            "Kilitli kalpleri hidayetle, kapalı yolları hayırla açan sonsuz fethin sahibidir. Hak ile batılı birbirinden ayırır. Dara düştüğümüzde bu ismi anmak yeni ufuklar müjdeler.",
            EsmaCategory.RAHMET
        ),
        EsmaName(
            20, "El-Alîm", "الْعَلِيمُ", "el-Alîm",
            "Zaman ve mekan sınırı olmadan, gizliyi, açığı, olmuş ve olacak her şeyi bilen.",
            "O can damarımızdan yakındır, zihnimizden geçen sessiz fısıltıları dahi hakkıyla bilir. O'nun huzurunda olduğumuzu bilerek yaşamak, insana en derin edebi kazandırır.",
            EsmaCategory.ILIM
        ),
        EsmaName(
            21, "El-Kâbıd", "الْقَابِضُ", "el-Kâbıd",
            "Dilediğinin rızkını, neşesini alan, kalpleri daraltan, sıkan ve imtihan eden.",
            "Bazen kalbimize hüzün ya da malımıza darlık gelir. Bu, bizi terbiye etmek, rücu ettirmek ve olgunlaştırmak içindir. Sabretmeyi ve O'na sığınmayı öğreniriz.",
            EsmaCategory.KUDRET
        ),
        EsmaName(
            22, "El-Bâsıt", "الْبَاسِطُ", "el-Bâsıt",
            "Dilediğinin rızkını bollaştıran, kalplerine ferahlık, genişlik ve neşe veren.",
            "Huzur, bereket ve genişlik O'nun ihsanıyla gelir. Kalbimizin en daraldığı anlarda, el-Basıt ismi tecelli eder ve ruhumuzu esenliğe kavuşturur.",
            EsmaCategory.RAHMET
        ),
        EsmaName(
            23, "El-Hâfıd", "الْخَافِضُ", "el-Hâfıd",
            "Zalimleri, kibirlileri aşağıya indiren, derecelerini alçaltan.",
            "Zulmedenlerin veya kendini üstün görenlerin kibir kulelerini yıkan. Bu ismi tefekkür etmek, kibirden şiddetle kaçınmamız gerektiğini bize hatırlatır.",
            EsmaCategory.KUDRET
        ),
        EsmaName(
            24, "Er-Râfi", "الرَّافِعُ", "er-Râfi",
            "Müminleri, salih kulları yücelten, derecelerini arttıran ve onurlandıran.",
            "İman ve ahlak ile kendisini arayanları, samimi kulları göklere çıkaran ve aziz kılandır. Gerçek yükseliş maddi değil, manevi rütbelerdedir.",
            EsmaCategory.KUDRET
        ),
        EsmaName(
            25, "El-Muiz", "الْمُعِزُّ", "el-Muiz",
            "Dilediğini aziz kılan, ona şeref, üstünlük ve izzet veren.",
            "İnsanların övgüsüyle değil, Allah'ın takdiriyle şereflenmektir. Onur ve şahsiyetimizi koruyarak yaşamak, O'nun rızasını gözetmekle mümkündür.",
            EsmaCategory.KUDRET
        ),
        EsmaName(
            26, "El-Müzil", "الْمُذِلُّ", "el-Müzil",
            "Dilediğini zillete düşüren, rezil eden, boyun eğdiren.",
            "Haktan sapıp zulme meyledenleri alçaltan adaletin tecellisidir. Dünya şatafatına aldanıp haksızlık yapanların sonunu ihtar eden heybetli bir isimdir.",
            EsmaCategory.KUDRET
        ),
        EsmaName(
            27, "Es-Semî", "السَّمِيعُ", "es-Semî",
            "Her sesi, fısıltıyı, kalpten geçenleri en ince ayrıntısıyla işiten.",
            "Biz fısıldasak da O bizi duyar. İçten gelen dualarımızı, tövbelerimizi, çaresiz yakarışlarımızı işitendir. Dua ederken yalnız olmadığımızın en büyük teminatıdır.",
            EsmaCategory.ILIM
        ),
        EsmaName(
            28, "El-Basîr", "الْبَصِيرُ", "el-Basîr",
            "Zifiri karanlıkta dahi her şeyi, her hareketi apaçık gören.",
            "O, karanlık bir gecede kara taşın üzerindeki kara karıncayı ve kalbindeki niyetleri görendir. Hiçbir şey O'ndan gizlenemez. Davranışlarımızı güzelleştirir.",
            EsmaCategory.ILIM
        ),
        EsmaName(
            29, "El-Hakem", "الْحَكَمُ", "el-Hakem",
            "Hükmeden, hakkı yerine getiren, mutlak adil ve hakem olan.",
            "Zulme geçit vermez, kullar arasında tam bir adaletle hüküm verir. Ahirette de dünyada da nihai karar verici O'dur. Bu isme uyanlar da adaletli davranır.",
            EsmaCategory.ILIM
        ),
        EsmaName(
            30, "El-Adl", "الْعَدْلُ", "el-Adl",
            "Mutlak adil olan, her şeyi yerli yerine koyan, asla haksızlık yapmayan.",
            "Evrendeki muazzam dengenin esası mutlak adalettir. Zulmetmekten ve haksızlıktan kaçınmak, el-Adl isminin ahlakını kuşanmaktır.",
            EsmaCategory.TENZIH
        ),
        EsmaName(
            31, "El-Latîf", "اللَّطِيفُ", "el-Latîf",
            "En ince işlerin detayını bilen, sezilmez yollarla kullarına lütufta bulunan.",
            "Kullarına hissettirmeden narin ikramlar gönderen, zorlukların içinden ince hayırlar çıkaran lütuf deryası. Umutsuz anlarda sessizce tecelli eden şefkattir.",
            EsmaCategory.RAHMET
        ),
        EsmaName(
            32, "El-Habîr", "الْخَبِيرُ", "el-Habîr",
            "Her şeyin iç yüzünden, gizli kalmış taraflarından haberdar olan.",
            "Hiçbir sır O'na gizli kalamaz. Kalbimizin en kuytu köşelerindeki hüzünlerden dahi haberdardır. O'na teslim olmak kalbe muazzam bir sekine verir.",
            EsmaCategory.ILIM
        ),
        EsmaName(
            33, "El-Halîm", "الْحَلِيمُ", "el-Halîm",
            "Yaratılanlara cezada acele etmeyen, yumuşaklık ve tahammül sahibi.",
            "Kulların isyanlarına ve hatalarına rağmen hemen cezalandırmayan, onlara tövbe etmesi için mühlet ve imkan veren engin hilm sahibi.",
            EsmaCategory.RAHMET
        ),
        EsmaName(
            34, "El-Azîm", "الْعَظِيمُ", "el-Azîm",
            "Akılların kavrayamayacağı kadar azametli, pek yüce ve büyük olan.",
            "Büyüklüğünün başlangıcı ve sonu yoktur. Bu ismi tefekkür etmek, O'nun önünde saygıyla eğilmeyi ve kalpte gerçek huşuyu uyandırmayı sağlar.",
            EsmaCategory.TENZIH
        ),
        EsmaName(
            35, "El-Gafûr", "الْغَفُورُ", "el-Gafûr",
            "Hataları ve günahları aşırı derecede bağışlayan, çok mağfiret eden.",
            "Bağışlamasının sınırı olmayandır. Tövbeleri kabul eden, bizi temizleyip yeniden başlatan ilahi şefkatin sığınağıdır. Umudu asla kaybettirmez.",
            EsmaCategory.RAHMET
        ),
        EsmaName(
            36, "Eş-Şekûr", "الشَّكُورُ", "eş-Şekûr",
            "Az iyiliğe çok mükâfat veren, şükürleri kabul eden, nimetlerini arttıran.",
            "Kullarının ufacık bir iyiliğini, bir tebessümünü dahi zayi etmez, katkat fazlasıyla ödüllendirir. Şükretmek nimetleri arttırdığı gibi kalbi de zenginleştirir.",
            EsmaCategory.RAHMET
        ),
        EsmaName(
            37, "El-Aliyy", "الْعَلِيُّ", "el-Aliyy",
            "En yüksek mertebede olan, her yönden yüce ve ulu olan.",
            "Zatıyla ve sıfatlarıyla tüm yaratılmışların üstünde olan, kendisine erişilemeyecek kadar yüce. Bizleri alçakgönüllülüğe ve manevi dikey yükselişe sevk eder.",
            EsmaCategory.TENZIH
        ),
        EsmaName(
            38, "El-Kebîr", "الْكَبِيرُ", "el-Kebîr",
            "Sonsuz büyüklük ve yücelik sahibi, zati büyüklüğü idrak edilemeyen.",
            "Her şey O'nun yanında küçüktür. Karşılaştığımız büyük zorluklar el-Kebir ismi karşısında birer toz tanesidir. Güç ve heybet kaynağımızdır.",
            EsmaCategory.KUDRET
        ),
        EsmaName(
            39, "El-Hafîz", "الْحَفِيظُ", "el-Hafîz",
            "Kainattaki her şeyi dengede tutan, amelleri kaydeden ve kötülüklerden koruyan.",
            "Vahşi doğadaki dengeden hücrelerimizin işleyişine kadar koruyan O'dur. Bu ismi anarak O'nun korumasına sığınır ve emaneti zayi etmeme bilinci ediniriz.",
            EsmaCategory.KUDRET
        ),
        EsmaName(
            40, "El-Mukît", "الْمُقِيتُ", "el-Mukît",
            "Her canlının gıdasını veren, rızka muktedir olan ve her şeyi koruyan.",
            "Canlıların hayatını sürdürmesi için gerekli maddi ve manevi tüm azıkları tayin eden ve ulaştırandır. Paylaşmayı ve adil bölüşmeyi öğretir.",
            EsmaCategory.YARATILIS
        ),
        EsmaName(
            41, "El-Hasîb", "الْحَسِيبُ", "el-Hasîb",
            "Kullarının hesabını gören, onlara kâfi gelen ve her amelin hesabını tutan.",
            "O, her kulunun hayat defterini en ince detayına kadar bilendir. 'Hasbünallah' (Allah bize yeter) demek, O'nun hasib sıfatına sığınarak huzur bulmaktır.",
            EsmaCategory.ILIM
        ),
        EsmaName(
            42, "El-Celîl", "الْجَلِيلُ", "el-Celîl",
            "Azamet, celal, asalet ve büyüklük sahibi olan.",
            "Korku ve hayranlık uyandıran mutlak haşmetin sahibidir. O'nun büyüklüğü karşısında boyun eğerek dünyadaki sahte otoritelerden özgürleşiriz.",
            EsmaCategory.KUDRET
        ),
        EsmaName(
            43, "El-Kerîm", "الْكَرِيمُ", "el-Kerîm",
            "Çok cömert, karşılıksız veren, keremi bol olan.",
            "İstemeden veren, hata yapsak da ikramını esirgemeyen benzersiz cömertlik. Bu ahlakla ahlaklanarak cömertliği hayat düsturu edinmeliyiz.",
            EsmaCategory.RAHMET
        ),
        EsmaName(
            44, "Er-Rakîb", "الرَّقِيبُ", "er-Rakîb",
            "Her şeyi her an gözeten, denetimi ve kontrolü altında tutan.",
            "Kulunun her kalp atışını ve amelini saniye saniye izleyip koruyandır. Rakib ismini bilmek insanda murakebe (otokontrol) ve derin bir farkındalık oluşturur.",
            EsmaCategory.ILIM
        ),
        EsmaName(
            45, "El-Mucîb", "الْمُجِيبُ", "el-Mucîb",
            "Duaları, yakarışları ve istekleri kabul edip cevap veren.",
            "Çaresizce uzanan hiçbir eli boş çevirmeyen, feryadımıza icabet edendir. Dua etmek, el-Mucib ile kurulan en samimi bağdır.",
            EsmaCategory.RAHMET
        ),
        EsmaName(
            46, "El-Vâsi", "الْوَاسِعُ", "el-Vâsi",
            "İlmi, merhameti, rahmeti ve ihsanı her şeyi kuşatacak kadar geniş olan.",
            "Zorluklar, darlıklar O'nun geniş kollarında erir. İlmi ve lütfu sonsuzdur. Kalbimize genişlik ve sabır veren, sınırlara hapsolmamızı engelleyen isimdir.",
            EsmaCategory.RAHMET
        ),
        EsmaName(
            47, "El-Hakîm", "الْحَكِيمُ", "el-Hakîm",
            "Yaptığı her işi hikmetli olan, her şeyin arkasındaki gizli hayrı bilen.",
            "Kainattaki hiçbir şey abes ve gereksiz değildir. Karşılaştığımız olaylardaki hayır ve şer dengesini anlamak zihnimize sükunet getirir. O en doğrusunu yapar.",
            EsmaCategory.ILIM
        ),
        EsmaName(
            48, "El-Vedûd", "الْوَدُودُ", "el-Vedûd",
            "Kullarını çok seven, sevilmeye en layık olan ve sevgiyi yaratan.",
            "Kainatı ayakta tutan yegane harç sevgidir. O'nun sevgisi karşılıksız ve şifadır. Bir zerre sevginin dahi kaynağı O'dur. Kalbimizi muhabbetle doldurur.",
            EsmaCategory.RAHMET
        ),
        EsmaName(
            49, "El-Mecîd", "الْمَجِيدُ", "el-Mecîd",
            "Şerefi ulu, lütuf ve ihsanı bol olan, övülmeye en çok layık ulu padişah.",
            "Büyüklükte ve cömertlikte benzeri olmayandır. O'nu tespih etmek, ruhumuzu asilleştirir ve bizi dünya seviyesindeki çirkinliklerden arındırır.",
            EsmaCategory.KUDRET
        ),
        EsmaName(
            50, "El-Bâis", "الْبَاعِثُ", "el-Bâis",
            "Ölüleri dirilten, peygamberler gönderen, kalpleri gaflet uykusundan uyandıran.",
            "Yalnızca ahiretteki diriliş değil; yılgın ruhlarımızın azimle uyanışı da bu ismin vesilesiyle olur. Umutsuzluğa düşen kalbe hayat üfler.",
            EsmaCategory.YARATILIS
        ),
        EsmaName(
            51, "Eş-Şehîd", "الشَّهِيدُ", "eş-Şehîd",
            "Her şeye şahit olan, her şeyi apaçık gözlemleyen, hiçbir şey kendisinden kaçmayan.",
            "Adaletin en büyük şahididir. Dünyada kimsenin görmediği iyilikleri de kötülükleri de kaydedendir. O'nun şahitliği mümin için güven, zalim için uyarıdır.",
            EsmaCategory.ILIM
        ),
        EsmaName(
            52, "El-Hakk", "الْحَقُّ", "el-Hakk",
            "Varlığı hakiki olan, hakkın, adaletin ve doğrunun ta kendisi olan sarsılmaz gerçek.",
            "Bütün sahtelikler ve maskeler düşer, geriye yalnızca el-Hakk kalır. Adaletin ve gerçekliğin sığındığı limandır. Hayatta doğrudan yana olmamızı emreder.",
            EsmaCategory.TENZIH
        ),
        EsmaName(
            53, "El-Vekîl", "الْوَكِيلُ", "el-Vekîl",
            "Kendisine güvenilip dayanılan, işleri yoluna koyan mutlak sığınak.",
            "O'na tevekkül etmek, hayatın ağır yüklerini O'nun merhametli omuzlarına devretmektir. Kul elinden geleni yapar ve 'hasbünallah ve ni'mel vekil' der, huzur bulur.",
            EsmaCategory.RAHMET
        ),
        EsmaName(
            54, "El-Kaviyy", "الْقَوِيُّ", "el-Kaviyy",
            "Gücü ve kudreti sınırsız olan, her şeye muktedir, asla yorulmayan.",
            "Yorulmayan ve zayıflamayan mutlak kuvvettir. O'nun gücü karşısında her zorluk dize gelir. Kendi acizliğimizi bilip O'nun sonsuz kudretine güveniriz.",
            EsmaCategory.KUDRET
        ),
        EsmaName(
            55, "El-Metîn", "الْمَتِينُ", "el-Metîn",
            "Çok güçlü, sarsılmaz, metanet sahibi ve sarsıntıya uğramayan.",
            "Kuvvetinin şiddetinde sarsılma yaşamayandır. O'na inananların da hayat karşısında metanetli, kararlı ve sarsılmaz bir duruş sergilemesi gerekir.",
            EsmaCategory.KUDRET
        ),
        EsmaName(
            56, "El-Velî", "الْوَلِيُّ", "el-Velî",
            "Müminlerin dostu, yardımcısı, onları karanlıklardan aydınlığa çıkaran hamisi.",
            "O en vefakâr dosttur. Yalnız kaldığımızı düşündüğümüz anlarda dahi yanımızda olan hakiki sırdır. O'nu dost edinen asla kaybetmez.",
            EsmaCategory.RAHMET
        ),
        EsmaName(
            57, "El-Hamîd", "الْحَمِيدُ", "el-Hamîd",
            "Her türlü övgüye, hamde ve şükre layık olan yegâne velinimet.",
            "Yaptığı her iş övgüye layıktır. Başımıza ne gelirse gelsin, arkasındaki hayırlara hamdetmek bu ismin tefekkürüyle kalbimize yerleşir.",
            EsmaCategory.TENZIH
        ),
        EsmaName(
            58, "El-Muhsî", "الْمُحْصِي", "el-Muhsî",
            "Yarattığı her şeyin sayısını, miktarını ve detayını teker teker bilen.",
            "Nefes sayımızdan, başımızdaki saç teline, denizlerdeki damlalara kadar sayıp hesaba dökendir. Hayatımızın her anının kayıt altında olduğunu hatırlatır.",
            EsmaCategory.ILIM
        ),
        EsmaName(
            59, "El-Mübdî", "الْمُبْدِئُ", "el-Mübdî",
            "Maddesiz ve örneksiz olarak yaratmayı ilk kez başlatan.",
            "Hayatın kıvılcımını sıfırdan yakandır. Yenilik yapmaktan, sıfırdan başlamaktan korkmamalı, O'nun yaratış şevkiyle hayırlı adımlar atmalıyız.",
            EsmaCategory.YARATILIS
        ),
        EsmaName(
            60, "El-Muîd", "الْمُعِيدُ", "el-Muîd",
            "Yarattığı canlıları öldürdükten sonra tekrar diriltecek olan.",
            "Hiçbir şey yok olup gitmez. O, bizi ahirette yeniden inşa edecektir. Bu dünya hayatının geçiciliğini ve ebedi hayata hazırlığı simgeler.",
            EsmaCategory.YARATILIS
        ),
        EsmaName(
            61, "El-Muhyî", "الْمُحْيِي", "el-Muhyî",
            "Hayat veren, can bağışlayan, ölü bedenlere ve cansız ruhlara can üfleyen.",
            "Hayatın kaynağıdır. Bedenimize can verdiği gibi, zikriyle solmuş kalplerimize de hayat verir. O'na yönelen ruhlar her dem taze kalır.",
            EsmaCategory.YARATILIS
        ),
        EsmaName(
            62, "El-Mümît", "الْمُمِيتُ", "el-Mümît",
            "Ölümü yaratan, her canlıyı vakti geldiğinde dünyadan terhis eden.",
            "Ölüm bir son değil; hakiki dosta açılan kapı ve dünyadaki imtihanın bitişidir. Ölümü tefekkür etmek, insanı hırslardan ve adaletsizlikten alıkoyar.",
            EsmaCategory.KUDRET
        ),
        EsmaName(
            63, "El-Hayy", "الْحَيُّ", "el-Hayy",
            "Daima diri, uykudan ve yorgunluktan münezzeh, hayatın kaynağı olan.",
            "Ölümsüz ve ezeli hayat sahibidir. Fanilere bel bağlamak yerine, her an uyanık ve diri olan el-Hayy ismine dayanmak sarsılmaz bir teslimiyet verir.",
            EsmaCategory.TENZIH
        ),
        EsmaName(
            64, "El-Kayyûm", "الْقَيُّومُ", "el-Kayyûm",
            "Kendi kendine kaim olan, kainatı, gökleri ve yeri ayakta tutan yönetici.",
            "Bütün evren O'nun kudreti ve idaresiyle düzen içinde durur. El-Kayyum ismini anmak, işlerimizde tertipli, sorumlu ve adil durmamızı öğütler.",
            EsmaCategory.KUDRET
        ),
        EsmaName(
            65, "El-Vâcid", "الْوَاجِدُ", "el-Vâcid",
            "Zenginliğinden hiçbir şey eksilmeyen, dilediğini dilediği an bulan.",
            "Hiçbir şeye muhtaç değildir. O'nun katında her şey hazırdır. Bizler de aradığımız huzur ve hakikati yalnızca O'nun yanında bulabileceğimizi bilmeliyiz.",
            EsmaCategory.KUDRET
        ),
        EsmaName(
            66, "El-Mâcîd", "الْمَاجِدُ", "el-Mâcîd",
            "Şerefi, keremi ve cömertliği pek yüce olan mutlak asalet sahibi.",
            "Bağışlaması ve lütufları bol olandır. Kulunu yüceltmekten, onu onurlandırmaktan hoşlanandır. Davranışlarımızda saygınlık ve asalet ister.",
            EsmaCategory.KUDRET
        ),
        EsmaName(
            67, "El-Vâhid", "الْوَاحِدُ", "el-وَاحِدُ",
            "Zatında, sıfatlarında ve fiillerinde tek olan, benzeri ve ortağı bulunmayan.",
            "Tevhid inancının kalbidir. Zihnimizi putlardan, dünyevi sahte bağlılıklardan arındırarak tek bir kapıya yönelmemizi sağlar.",
            EsmaCategory.TENZIH
        ),
        EsmaName(
            68, "Es-Samed", "الصَّمَدُ", "es-Samed",
            "Hiçbir şeye muhtaç olmayan, aksine her şeyin kendisine muhtaç olduğu yüce sığınak.",
            "Kainatın dert ortağıdır. Herkes O'na sığınırken, O hiçbir şeye ihtiyaç duymaz. Bu ismi anarak, başkalarına el açmak yerine samimiyetle O'na yöneliriz.",
            EsmaCategory.TENZIH
        ),
        EsmaName(
            69, "El-Kâdir", "الْقَادِرُ", "el-Kâdir",
            "Dilediğini dilediği gibi yapmaya gücü yeten, kudret sahibi.",
            "Ölçü koyan ve gücü her şeye yetendir. Çaresiz kaldığımızı düşündüğümüz durumlarda el-Kadir ismi bize tükenmez umut ve metanet sağlar.",
            EsmaCategory.KUDRET
        ),
        EsmaName(
            70, "El-Muktedir", "الْمُقْتَدِرُ", "el-Muktedir",
            "Kudreti üzerinde hiçbir güç olmayan, dilediği her şeyi kolaylıkla başaran.",
            "Gücünün son noktasıdır. O'nun için imkansız yoktur. Tevekkülümüzü bu obtains ile taçlandırdığımızda, fırtınalar karşısında dimdik dururuz.",
            EsmaCategory.KUDRET
        ),
        EsmaName(
            71, "El-Mukaddim", "الْمُقَدِّمُ", "el-Mukaddim",
            "Dilediğini öne alan, derecesini yükselten, öne geçiren.",
            "Salih kullarını, gayret edenleri liyakatle öne çıkarandır. Başarıyı elde ettiğimizde kibirlenmemeli, bunu O'nun bir takdimi olarak görmeliyiz.",
            EsmaCategory.KUDRET
        ),
        EsmaName(
            72, "El-Muahhir", "الْمُؤَخِّرُ", "el-Muahhir",
            "Dilediğini geriye bırakan, erteleyen, cezalandırmayı sonraya bırakan.",
            "Hayatımızdaki ertelemelerde bir hikmet vardır. Bazen geciken bir istek, bizim için büyük hayırlara gebedir. Sabır ve güvenimizi pekiştirir.",
            EsmaCategory.KUDRET
        ),
        EsmaName(
            73, "El-Evvel", "الْأَوَّلُ", "el-Evvel",
            "Varlığının başlangıcı olmayan, ezelî olan.",
            "Her şeyden önce var olandır. Kökenimiz O'dur. Bu ismi düşünmek, hayatımıza yön veren asıl kaynağın ve ilk niyetin önemini hatırlatır.",
            EsmaCategory.TENZIH
        ),
        EsmaName(
            74, "El-Âhır", "الْآخِرُ", "el-Âhır",
            "Varlığının sonu olmayan, ebedî olan.",
            "Her şey yok olduğunda baki kalacak olandır. Gideceğimiz son adres O'dur. Maddi kayıplara üzülmek yerine ebedi olan dosta yatırım yapmayı öğretir.",
            EsmaCategory.TENZIH
        ),
        EsmaName(
            75, "Ez-Zâhir", "الظَّاهِرُ", "ez-Zâhir",
            "Varlığı yarattığı eserlerle gözle görülen, aşikâr olan.",
            "Göklerin maviliği, çileklerin kokusu, tabiatın sanatı O'nun apaçık imzasıdır. Kainat kitabını bu gözle okumak tefekkürün zirvesidir.",
            EsmaCategory.YARATILIS
        ),
        EsmaName(
            76, "El-Bâtın", "الْبَاطِنُ", "el-Bâtın",
            "Duyularla algılanamayan, her şeyin iç yüzüne nüfuz eden, gizli olan.",
            "Gözlerden gizlenmiştir fakat kalplere çok yakındır. Gizemin, derinliğin ve içsel yolculuğun adıdır. Sessizce tefekkür etmeyi öğretir.",
            EsmaCategory.TENZIH
        ),
        EsmaName(
            77, "El-Vâlî", "الْوَالِي", "el-Vâlî",
            "Kainatı idare eden, işleri düzenleyen, gözeten mutlak yönetici.",
            "Kainat başıboş değildir. Karıncanın adımlarından galaksilerin dönüşüne kadar her idari nizam O'nun elindedir. O'nun yönetimine güvenmek huzur verir.",
            EsmaCategory.KUDRET
        ),
        EsmaName(
            78, "El-Müteâlî", "الْمُتَعَالِي", "el-Müteâlî",
            "Eksikliklerden tamamen arınmış, yaratılmışların üstünde sonsuz yüce.",
            "Aklın sınırlarını aşan ulu mertebenin adıdır. İnsanın kendi zihni sınırlarını bilerek saygıyla ve hayranlıkla secde etmesidir.",
            EsmaCategory.TENZIH
        ),
        EsmaName(
            79, "El-Berr", "الْبَرُّ", "el-Berr",
            "Kullarına iyilik ve kolaylık dileyen, lütfu, ihsanı bol olan.",
            "Kullarına her an güzellikler saçandır. O zorluk murat etmez, kolaylık diler. Bizler de insanlara karşı iyiliksever (berr) olmalıyız.",
            EsmaCategory.RAHMET
        ),
        EsmaName(
            80, "Et-Tevvâb", "التَّوَّابُ", "et-Tevvâb",
            "Tövbeleri kabul eden, kullarını her seferinde pişmanlıkla bağışlayan.",
            "Kaç kez hata yaparsak yapalım, samatî bir pişmanlıkla kapısına döndüğümüzde bizi kollarını açarak karşılar. Ümidi hep taze tutar.",
            EsmaCategory.RAHMET
        ),
        EsmaName(
            81, "El-Müntekım", "الْمُنْتَقِمُ", "el-Müntekım",
            "Suçluları, zalimleri adaletle cezalandıran, mazlumların hakkını alan.",
            "Haksızlık yapanların yanına kâr kalmayacağını gösteren adalettir. İntikam hissini şahsileştirmeyip ilahi adalete teslim olmak kalbi rahatlatır.",
            EsmaCategory.KUDRET
        ),
        EsmaName(
            82, "El-Afüvv", "الْعَفُوُّ", "el-Afüvv",
            "Günah izlerini tamamen silen, affı ve hoşgörüsü sınırsız olan.",
            "Sadece affetmekle kalmayıp, işlenen günahların izini, utancını da tamamen yok edendir. Bizlerin de kin tutmadan bağışlamasını öğütler.",
            EsmaCategory.RAHMET
        ),
        EsmaName(
            83, "Er-Raûf", "الرَّؤُوفُ", "er-Raûf",
            "Çok şefkatli, merhametin ötesinde narin bir sevgiyle yaklaşan acıyan.",
            "Merhametin en ince, en tatlı düzeyidir. Kullarını incitmeden, onlara tatlılıkla esenlik verendir. Kalbimizi nezaketle donatır.",
            EsmaCategory.RAHMET
        ),
        EsmaName(
            84, "Mâlikü'l-Mülk", "mâlikü'l-mülk", "Mâlikü'l-Mülk",
            "Mülkün mutlak ve ebedî sahibi olan, mülkünde dilediği gibi tasarruf eden.",
            "Varlığımız, bedenimiz, malımız mülkümüz emanettir. emanet bilinciyle yaşamak, kayıplar karşısında sabırlı, kazançlar karşısında mütevazı kılar.",
            EsmaCategory.KUDRET
        ),
        EsmaName(
            85, "Zü'l-Celâli ve'l-İkrâm", "ذُو الْجَلَالِ وَالْإِكْرَامِ", "Zü'l-Celâli ve'l-İkrâm",
            "Hem azamet ve celal sahibi hem de ikram ve kerem sahibi olan.",
            "Hem korkulmaya layık yücelikte hem de aşırı cömertlikle ikram buyurandır. Saygı ve muhabbeti bir arada barındıran muazzam bir dengedir.",
            EsmaCategory.KUDRET
        ),
        EsmaName(
            86, "El-Muksıt", "الْمُقْسِطُ", "el-Muksıt",
            "Adaletle hükmeden, mazlumların hakkını zalimlerden alan ve dengeyi kuran.",
            "Her hakkı sahibine teslim eder. Davranışlarımızda dengeli, ölçülü ve adil olmak bu ismin en samimi yansımasıdır.",
            EsmaCategory.ILIM
        ),
        EsmaName(
            87, "El-Câmi", "الْجَامِعُ", "el-Câmi",
            "Zıtlıkları bir araya getiren, kıyamette insanları toplayan, kalpleri birleştiren.",
            "Parçaları birleştiren, dostluk ve sevgi köprüleri kurandır. İnsanları hayırlı işlerde bir araya getirmek bu ismin ahlakıyla kuşanmaktır.",
            EsmaCategory.KUDRET
        ),
        EsmaName(
            88, "El-Ganî", "الْغَنِيُّ", "el-Ganî",
            "Hiçbir şeye ihtiyacı olmayan mutlak zengin, herkesin O'na muhtaç olduğu.",
            "Asıl zenginlik eşyaya sahip olmak değil, hiçbir şeye ihtiyaç duymama halidir. El-Gani ismini tefekkür etmek kalbi kanaatle zenginleştirir.",
            EsmaCategory.TENZIH
        ),
        EsmaName(
            89, "El-Mugnî", "الْمُغْنِي", "el-Mugnî",
            "Dilediğini zengin kılan, ihtiyacını gideren, kullarına refah veren.",
            "Maddi ve manevi ferahlık verendir. Elimizdekileri başkalarıyla paylaşarak el-Mugni isminin cömertlik kanalına dahil oluruz.",
            EsmaCategory.RAHMET
        ),
        EsmaName(
            90, "El-Mânî", "الْمَانِعُ", "el-Mânî",
            "Dilemediği şeylerin gerçekleşmesini engelleyen, korumak için vermeyen.",
            "Bazen bir kapının kapalı olması, bizim zarar görmememiz içindir. O, vermemekle de aslında lütfeder. Bu ismi anlamak isyan dindirir.",
            EsmaCategory.KUDRET
        ),
        EsmaName(
            91, "Ed-Dârr", "الضَّارُّ", "ed-Dârr",
            "Zarar veren şeyleri yaratan, dilediğini sıkıntıya ve zarara düşüren.",
            "Zatında haksız yere zarar veren sıfatı yoktur; fakat imtihan gereği sıkıntıların da yaratıcısı O'dur. Maddi manevi her hırpalanışta hidayet gizlidir.",
            EsmaCategory.KUDRET
        ),
        EsmaName(
            92, "En-Nâfi", "النَّافِعُ", "en-Nâfi",
            "Yarar sağlayan şeyleri yaratan, kullarına fayda ve şifalar bahşeden.",
            "Gıdaların şifası, toprağın bereketi, tebessümün neşesi hep en-Nafi isminin sirayetidir. Başkalarına faydalı olmak en yüce amaçtır.",
            EsmaCategory.YARATILIS
        ),
        EsmaName(
            93, "En-Nûr", "النُّورُ", "en-Nûr",
            "Kainatı aydınlatan, kalpleri iman nuruyla dolduran, karanlıkları yırtan.",
            "Hidayet ve doğruluk ışığıdır. O'nun nuru olmadığı sürece zihinler de ruhlar da karanlıkta kalır. Kalbimizi bu nura açmayı tefekkür etmeliyiz.",
            EsmaCategory.YARATILIS
        ),
        EsmaName(
            94, "El-Hâdî", "الْهَادِي", "el-Hâdî",
            "Hidayet veren, kullarını doğru yola, selamete ve huzura ulaştıran.",
            "Bizi kalabalıklar içinde kaybolmaktan koruyandır. Şaşkınlığa düştüğümüzde 'İhdinessiratal müstekim' deriz ve O'nun hidayetine tutunuruz.",
            EsmaCategory.RAHMET
        ),
        EsmaName(
            95, "El-Bedî", "الْبَدِيعُ", "el-Bedî",
            "Sanatında benzersiz olan, örneksiz ve hayranlık uyandırıcı güzellikler yaratan.",
            "Kainattaki her bir çiçeğin, her bir galaksinin eşsiz tasarımı O'nun sınırsız sanat gücüdür. Gören gözler için tam bir hayranlık kaynağıdır.",
            EsmaCategory.YARATILIS
        ),
        EsmaName(
            96, "El-Bâkî", "الْبَاقِي", "el-Bâkî",
            "Varlığının sonu olmayan, ebediyen kalacak olan tek gerçek.",
            "Her şey silinip gidecektir, ancak O ebedidir. Geçici olana kalbimizi bağlayıp mahzun olmak yerine baki olana tutunmanın huzurunu hissetmeliyiz.",
            EsmaCategory.TENZIH
        ),
        EsmaName(
            97, "El-Vâris", "الْوَارِثُ", "el-Vâris",
            "Geçici sahipleri gittikten sonra mülkün hakiki sahibi olan tek varis.",
            "Dünyadaki her sahiplik geçicidir. Sonunda her şey liyakatle O'na kalacaktır. Mülkle şımarmamayı ve emanetçi olduğumuzu bilmek gerekir.",
            EsmaCategory.KUDRET
        ),
        EsmaName(
            98, "Er-Reşîd", "الرَّشِid", "er-Reşîd",
            "Kullarını doğru yola yönlendiren, her işi isabetli ve mükemmel olan.",
            "İrşad eden, doğruluğun kaynağıdır. O'nun gösterdiği yolda olanlar asla şaşırmaz. Kararlarımızda daima sağduyu ve rüşt aramalıyız.",
            EsmaCategory.ILIM
        ),
        EsmaName(
            99, "Es-Sabûr", "الصَّبُورُ", "es-Sabûr",
            "Cezalandırmada acele etmeyen, kullarına sabır veren sonsuz sabır sahibi.",
            "Hatalarımız karşısında acele öfke göstermeyip bize zaman verendir. Bize sabırlı, sakin ve tahammüllü olmayı fıtrat edinmemizi tavsiye eder.",
            EsmaCategory.RAHMET
        )
    )
    
    fun getById(id: Int): EsmaName? {
        return ALL_NAMES.find { it.id == id }
    }
}
