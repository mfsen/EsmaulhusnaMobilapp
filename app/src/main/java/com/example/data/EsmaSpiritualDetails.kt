package com.example.data

data class TheologicalDetails(
    val id: Int,
    val name: String,
    val quranReference: String,
    val hadithReference: String,
    val reflectionGuide: String,
    val divineSecret: String
)

object EsmaSpiritualDetails {

    fun getDetails(esma: EsmaName): TheologicalDetails {
        val id = esma.id
        val name = esma.name
        
        // Custom deeply detailed responses for key names
        return when (id) {
            1 -> TheologicalDetails(
                id = id,
                name = name,
                quranReference = "Haşr Suresi 22-24. ayetlerde ve Bakara Suresi 255. ayette (Ayetel Kürsi) en yüce ifadeyle geçer: 'O, kendisinden başka hiçbir ilah olmayan Allah’tır.' Ayrıca İhlas Suresi baştan sona bu yüce ismi ve tevhid hakikatini açıklar.",
                hadithReference = "Hz. Peygamber (s.a.v.) şöyle buyurmuştur: 'Allah’ın doksan dokuz ismi vardır, yüz birdir eksik. Kim bunları ezberler, idrak eder ve hayatına yansıtırsa cennete girer.' (Buhari, Şurût, 18; Müslim, Zikir, 5).",
                reflectionGuide = "Allah lafzı, tüm esma ve sıfatları kendisinde barındıran zattır. Bu isimle tefekkür ederken, kalpteki tüm sahte sevgileri ve korkuları silerek sadece O'nun rızasına yönelmek hedeflenir. Günde bir süre sessizce sadece 'Allah' zikrine odaklanmak, kalbe huzur ve tevhid nurunu yerleştirir.",
                divineSecret = "Yâ Allah zikri, ruhtaki tüm düğümleri çözen, marifetullah (Allah'ı bilme) kapılarını aralayan en büyük anahtardır. Sıkıntılı anlarda tüm sıfatların kaynağına sığınmaktır."
            )
            2 -> TheologicalDetails(
                id = id,
                name = name,
                quranReference = "Rahman Suresi bütünüyle bu ismin tecellilerini anlatır. Ayrıca İsra Suresi 110. ayette geçer: 'De ki: İster Allah diye dua edin, ister Rahman diye dua edin. Hangisiyle dua ederseniz edin, en güzel isimler O'nundur.'",
                hadithReference = "Kudsi bir hadiste Allah Teâlâ şöyle buyurur: 'Ben Rahman’ım, rahmi (akrabalık bağını) Ben yarattım ve ona Kendi ismimden türettiğim adımı verdim. Kim sıla-i rahim yaparsa ona merhamet ederim.' (Tirmizi, Birr, 16).",
                reflectionGuide = "Er-Rahmân ismi dünyadaki tüm canlıları kuşatır. Bu ismi tefekkür eden bir kul, yeryüzündeki hiçbir canlıya, hayvana veya bitkiye zarar veremez. O'nun sonsuz şefkatini düşünerek, bizler de kalbimizdeki şefkat pınarlarını uyandırmalıyız.",
                divineSecret = "Yâ Rahmân zikri, öfkeyi dindiren, asabi ve gergin hisleri huzura kavuşturan, insanlar arasındaki buzları eriten mucizevi bir tecelliye sahiptir."
            )
            3 -> TheologicalDetails(
                id = id,
                name = name,
                quranReference = "Ahzab Suresi 43. ayette müminlere özel tecelli olarak zikredilir: 'O, müminlere karşı çok merhametlidir (Rahîm).' Ayrıca her surenin başındaki Besmele'de bu isim yer alır.",
                hadithReference = "Hz. Peygamber (s.a.v.) bir hadisinde şöyle buyurur: 'Allah merhametini yüz parçaya böldü. Doksan dokuzunu kendi katında tuttu, bir parçayı ise yeryüzüne indirdi. İşte canlılar bu bir parça ile birbirine acır.' (Buhari).",
                reflectionGuide = "Er-Rahîm tecellisi, kulun iman ve samimiyetiyle kazandığı özel bir nimettir. Bu isimle tefekkür ederken, ahiret bilincimizi tazelemeli ve yaptığımız işleri sadece geçici dünya hayatı için değil, ebedi mutluluk için mükemmelleştirmeye niyet etmeliyiz.",
                divineSecret = "Yâ Rahîm zikri, darlık zamanlarında genişlik ulaştıran, kalbe ve haneye bereket, huzur ve manevi koruma sağlayan asil bir kalkandır."
            )
            4 -> TheologicalDetails(
                id = id,
                name = name,
                quranReference = "Taha Suresi 114. ayette azametiyle zikredilir: 'Hakiki hükümdar olan Allah pek yücedir.' Ayrıca Haşr Suresi 23. ve Cuma Suresi 1. ayetlerinde kainatın gerçek yöneticisi olarak takdim edilir.",
                hadithReference = "Resulullah (s.a.v.) şöyle buyurmuştur: 'Kıyamet günü Allah Teâlâ yerleri kabza-i kudretine alır, gökleri de sağ elinde dürer ve: Melik (Gerçek Kral) Benim, nerede yeryüzünün hükümdarları? buyurur.' (Buhari, Tevhid, 19).",
                reflectionGuide = "El-Melik esmasını düşünen bir insan, dünyadaki geçici mülklere, paralara ve makamlara kalbini kaptırmaz. Kendisinin de dünyevi bedeninin de geçici birer emanet olduğunu anlar. Bu kul, kula kul olmaktan kurtularak özgürleşir.",
                divineSecret = "Yâ Melik zikri, nefsin isteklerini kontrol altına almaya, iradeyi güçlendirip rızık endişesinden kurtulmaya vesile olan heybetli bir zikirdir."
            )
            5 -> TheologicalDetails(
                id = id,
                name = name,
                quranReference = "Haşr Suresi 23. ayette ve Cuma Suresi 1. ayette 'El-Melikü'l-Kuddûs' (Eksikliklerden uzak, kutlu hükümdar) şeklinde geçer.",
                hadithReference = "Hz. Peygamber (s.a.v.) vitir namazını bitirip selam verdiğinde üç defa sesini yükselterek şöyle derdi: 'Sübhâne'l-Meliki'l-Kuddûs' (Mülkün sahibi ve her türlü noksanlıktan münezzeh olan Allah'ı tesbih ederim). (Nesai, Sehiv, 82).",
                reflectionGuide = "El-Kuddûs ismiyle tefekkür eden kul, fiziki temizliğin ötesinde, iç dünyasını gıybet, yalan, haset, kibir gibi manevi kirlerden arındırma çabasına girer. Kalbini Allah'ın sevgisi için bembeyaz bir mabede dönüştürür.",
                divineSecret = "Yâ Kuddûs zikri, kötü alışkanlıklardan ve takıntılardan kurtulmak, zihni vesveselerden temizlemek için her gün huşuyla çekilir."
            )
            6 -> TheologicalDetails(
                id = id,
                name = name,
                quranReference = "Haşr Suresi 23. ayette esenlik ve selamet veren sıfatıyla geçer. Ayrıca Yasin Suresi 58. ayette cennet ehline bir hitap olarak zikredilir: 'Çok merhametli olan Rab'den bir söz olarak onlara selam vardır.'",
                hadithReference = "Sevban (r.a.) anlatıyor: Resulullah (s.a.v.) namazdan çıkınca üç kez istiğfar eder ve şöyle buyururdu: 'Allahümme entes-selâmü ve minkes-selâm tebârakte yâ zel-celâli vel-ikrâm' (Allah'ım Sen selamsın, selamet Sendendir...). (Müslim).",
                reflectionGuide = "Es-Selâm ismini tefekkür eden kimse, çevresine güven ve esenlik verir. Elinden ve dilinden kimsenin zarar görmediği emin bir Müslüman olur. Kavga ve kaos yerine daima uzlaşıyı, barışı ve nezaketi benimser.",
                divineSecret = "Yâ Selâm zikri, korkulardan, panik ve anksiyeteden kurtulmak, hastalara şifa ve ruhlara huzur yaymak adına çok güçlü bir esenlik kaynağıdır."
            )
            12 -> TheologicalDetails(
                id = id,
                name = name,
                quranReference = "En'am Suresi 102. ayette buyrulur: 'İşte Rabbiniz Allah budur. O'ndan başka ilah yoktur. O, her şeyin yaratıcısıdır (Hâlık). O'na kulluk edin.' Hicr Suresi 86. ayette de 'Şüphesiz Rabbin her şeyi yaratan ve hakkıyla bilendir' denir.",
                hadithReference = "Hâlık ismi, varlığın sıfırdan ve mükemmel bir nizamla inşasını ifade eder. Resulullah (s.a.v.) yaratılışın ihtişamı ve tefekkürü üstüne her gün dualarında Allah'ın yaratıcılığını yüceltmiştir.",
                reflectionGuide = "Doğaya, gökyüzüne, kendi vücudumuzdaki organların uyumuna baktığımızda El-Hâlık isminin damgasını görürüz. Bu esmayla tefekkür, dünyadaki hiçbir şeyin başıboş ve tesadüf eseri olmadığını anlamamızı sağlar.",
                divineSecret = "Yâ Hâlık zikri, işlerinde çıkmaza giren, yeni bir girişim veya proje tasarlayan kişilerin ilham ve doğruluk yeteneğini arttırır."
            )
            15 -> TheologicalDetails(
                id = id,
                name = name,
                quranReference = "Taha Suresi 82. ayette müjdelenir: 'Şüphe yok ki ben, tövbe eden, inanan, salih amel işleyen, sonra da doğru yolda giden kimseyi çokça bağışlarım (Gaffâr).'",
                hadithReference = "Hz. Peygamber (s.a.v.) şöyle dua ederdi: 'Allah'ım! Günahımı bağışla, bana merhamet et, tövbemi kabul buyur. Şüphesiz sen tövbeleri çok kabul eden ve çok bağışlayansın.'",
                reflectionGuide = "El-Gaffâr ismini tefekkür eden bir mümin, geçmiş hatalarının ağırlığından ezilmek yerine ümitle tövbe kapısına yönelir. Aynı zamanda, kendisine hata yapan insanları affederek, onlara karşı şefkat göstererek bu esmanın ahlakıyla kuşanır.",
                divineSecret = "Yâ Gaffâr zikri, vicdan azaplarını teskin eden, geçmişin pişmanlık zincirlerini kıran ve ruhu hafifleten bir mağfiret kapısıdır."
            )
            20 -> TheologicalDetails(
                id = id,
                name = name,
                quranReference = "Bakara Suresi 282. ayetin sonunda: 'Allah her şeyi hakkıyla bilendir' buyrulur. Kaf Suresi 16. ayette ise insanın iç fısıltılarının dahi bilindiği söylenir: '...Biz ona şah damarından daha yakınız.'",
                hadithReference = "Resulullah (s.a.v.) dualarında şöyle sığınırdı: 'Allah'ım! Faydasız ilimden, korkmayan kalpten, doymayan nefisten ve kabul olunmayan duadan sana sığınırım.' (Müslim, Zikir, 73).",
                reflectionGuide = "El-Alîm ismini tefekkür etmek, insanda muazzam bir otokontrol (murakebe) bilinci oluşturur. Gizli saklı hiçbir şeyin Allah'tan kaçamayacağını bilen kul, yalnız kaldığında dahi ahlakını bozmaz, dürüstlükten ayrılmaz.",
                divineSecret = "Yâ Alîm zikri, zihni açar, anlama ve kavrama gücünü yükseltir. Derslerde, akademik çalışmalarda ve manevi ilimlerde zirveye ulaşmayı kolaylaştırır."
            )
            48 -> TheologicalDetails(
                id = id,
                name = name,
                quranReference = "Hud Suresi 90. ayette: 'Şüphesiz Rabbim çok merhametlidir, kullarını çok sevendir (Vedûd).' ayetiyle sevginin kaynağı açıklanır. Ayrıca Buruc Suresi 14. ayette bağışlayan ve seven anlamıyla yan yana zikredilir.",
                hadithReference = "Resulullah (s.a.v.) şöyle buyurmuştur: 'Allah bir kulu sevdiği zaman Cebrail'e: Ben filancayı seviyorum, sen de onu sev! buyurur. Cebrail de onu sever, sonra gök ehline nida eder ve yeryüzüne onun sevgisi kabul olarak indirilir.' (Buhari).",
                reflectionGuide = "El-Vedûd ismi, kainatın yaratılış mayası olan sevgiyi temsil eder. Bu ismi düşünen kul, her şeye muhabbet gözüyle bakar. Yaradılanı Yaradandan ötürü sever. Evrendeki her çiçekte, çocuk gülüşünde ve anne şefkatinde El-Vedûd'un tecellisini görür.",
                divineSecret = "Yâ Vedûd zikri, aile içindeki huzursuzlukları bitiren, kalpleri sevgi ve ünsiyet ile dolduran, yalnızlık hissini kalpten söküp atan muazzam bir aşk ve muhabbet esmasıdır."
            )
            
            // Rich fallback generator for any of the other 99 names based on their category
            else -> {
                val catDetails = when (esma.category) {
                    EsmaCategory.RAHMET -> CatInfo(
                        quran = "Bu mübarek isim, Kur'an-ı Kerim'in şefkat, mağfiret ve ilahi ihsan ayetlerinde sıkça zikredilir. Yüce rabbimizin kullarını kucaklayan, dertlerini hafifleten merhamet ikliminin bir nişanesidir. (Örn: A'râf Suresi 156. ayet: 'Rahmetim her şeyi kuşatmıştır.')",
                        hadith = "Kutlu Nebi (s.a.v.) dualarında bu güzel ismi anarak Allah'ın cömertliğine ve ikramlarına iltica etmiş, 'Allah'ım, senin esirgemene ve şefkatine sığınıyorum' niyazıyla ümmetine yol göstermiştir.",
                        guide = "Bu ismi tefekkür etmek, kalpteki katılığı ve merhametsizliği yok eder. Bize muhtaç olanlara yardım etmeye, darda kalmış bir cana yardım eli uzatmaya yönlendirir. İnsanlara karşı tebessüm ve samimiyetle yaklaşmak bu esmanın hayattaki yansımasıdır.",
                        secret = "Yâ ${esma.name} zikri, ruha esenlik kazandırır, rızık darlığını ve ikili ilişkilerdeki soğukluğu gidererek yaşamı güzelleştirir."
                    )
                    EsmaCategory.KUDRET -> CatInfo(
                        quran = "Yüce yaratıcının her şeye boyun eğdiren, dilediğini mutlak surette gerçekleştiren sonsuz gücünü anlatan ayetlerde yer bulur. Göklerin ve yerin ordularının, depremlerin, yıldızların ulu nizamının arkasındaki mutlak faili ilan eder. (Örn: Âl-i İmrân Suresi 26. ayet).",
                        hadith = "Peygamber Efendimiz (s.a.v.) sıkıntıya uğradığı vakitlerde bu esmanın ihtişamına dayanmış, 'Kudret sahibi olan ancak Sensin, senden başka güç yoktur' kelimeleriyle teslimiyetin en yüce zirvesini göstermiştir.",
                        guide = "Kudret esmaları ile tefekkür eden insan, dünyevi hiçbir güçten, zalimden veya patronajdan korkmaz. Hakiki sığınağın Allah olduğunu bildiği için dik duruşunu bozmaz. Karşılaştığı zorluklar karşısında 'Bu da geçer, Allah kadirdir' diyerek metanetini korur.",
                        secret = "Yâ ${esma.name} zikri, korkuları cesarete, zayıflığı güce ve metanete çeviren, hayat mücadelesinde yılgınlığı önleyen asil bir kalkandır."
                    )
                    EsmaCategory.ILIM -> CatInfo(
                        quran = "Bu esma, kainattaki her zerre hareketin, yaprağın düşüşünün, kalpteki gizli niyetlerin dahi yüce yaratıcı tarafından saniye saniye izlendiğini ve bilindiğini açıklayan ayetlerde zikredilir. (Örn: Lokman Suresi 16. ayet).",
                        hadith = "Resulullah (s.a.v.) her işinde istihare duasını öğreterek Allah'ın gaybı ve geleceği bilen eşsiz ilmine başvurmuş, 'Sen bilirsin, ben bilmem. Sen güç yetirirsin, ben yetiremem' diyerek ilahi ilme ram olmuştur.",
                        guide = "İlim ve hüküm esmaları ile tefekkür etmek, insanı gizli günahlardan ve riyakarlıktan (gösterişten) korur. İnsanda muazzam bir derin farkındalık ve sorumluluk şuuru uyandırır. Her anımızın kayıt altında olduğu gerçeği, yaşamımıza bir disiplin ve zarafet katar.",
                        secret = "Yâ ${esma.name} zikri, akıl ve fehmi (kavramayı) keskinleştirir, hafızayı güçlendirir ve kararsız kaldığımız anlarda en doğru adımı fıtratımıza ilham eder."
                    )
                    EsmaCategory.YARATILIS -> CatInfo(
                        quran = "Evrendeki kusursuz biyolojik dengenin, doğadaki rengarenk estetiğin ve insanın yoktan var edilip rızıklandırılmasının yüce sırlarını açıklayan ayetlerde zikredilir. (Örn: Vakıa Suresi ve Rum Suresi ayetleri).",
                        hadith = "Efendimiz (s.a.v.) doğadaki her uyanış ve mevsim değişimini tefekkür ederek 'Yaratanların en güzeli olan Allah pek kutludur' ifadesiyle şükreylemiş ve nimete saygıyı öğütlemiştir.",
                        guide = "Bu ismi tefekkür eden bir göz, kainata bir turist gibi değil, bir sanat galerisine bakar gibi hayranlıkla bakar. Topraktan çıkan bir çiçeği, arının bal yapmasını, yağmur tanelerinin süzülmesini ibretle izler ve şükreder.",
                        secret = "Yâ ${esma.name} zikri, helal rızkın artmasına, berekete, kısırlık ve verimsizliklerin ortadan kalkıp hayırlı meyveler vermeye vesile olur."
                    )
                    EsmaCategory.TENZIH -> CatInfo(
                        quran = "Yüce Allah'ın insan aklının tasavvur edebileceği her türlü eksiklikten, acizlikten, fani sıfatlardan bütünüyle pak ve münezzeh olduğunu ilan eden tespih ayetlerinde yer alır. (Örn: İhlas Suresi ve Haşr Suresi son ayetleri).",
                        hadith = "Hz. Peygamber (s.a.v.) rükuda 'Sübhâne Rabbiye’l-Azîm', secdede 'Sübhâne Rabbiye’l-A’lâ' diyerek temizliği ve yoksan sıfatlardan uzaklığı daima tespih etmiştir.",
                        guide = "Tenzih esmaları ile kurulan tefekkür köprüsü, tevhid inancını saflaştırır. Allah'ı maddeleştirmekten, O'na fani sınırlar çizmekten bizi korur. O'nun eşsiz şanına layık bir tazim ile eğilmeyi, kendi nefsimizin kırılganlığını ve kusurlarını kabullenmeyi öğretir.",
                        secret = "Yâ ${esma.name} zikri, nefsin şımarıklığını, kibri ve egoyu yerle bir eder; kalbe mütevazılık, sükunet ve yüksek manevi berraklık aşılar."
                    )
                }
                
                TheologicalDetails(
                    id = id,
                    name = name,
                    quranReference = catDetails.quran,
                    hadithReference = catDetails.hadith,
                    reflectionGuide = catDetails.guide,
                    divineSecret = catDetails.secret
                )
            }
        }
    }

    private data class CatInfo(
        val quran: String,
        val hadith: String,
        val guide: String,
        val secret: String
    )
}
