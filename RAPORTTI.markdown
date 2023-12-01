# Raportit tehtävistä

Kirjaa tähän tiedostoon **jokaiseen** tehtävään liittyvät omat raporttisi ja analyysisi. Muista että raportti on myös kurssilla **arvosteltava tehtävä**.

Voit sisällyttää raporttiin tekstimuotoisia taulukoita (tasaukset välilyönnein):

```
n     Fill     Search   Total
500   7        700      707
1000  9        288      297
```

Ja näihin liittyviä kuvatiedostoja:

![Esimerkkikuva](report-sample-image.png)

Nämä näkyvät sitten VS Coden Preview -näkymässä (tai oman repositorysi webbisivulla) oikein muotoiltuna. Käytä tässä dokumentissa olevia muotoiluja esimerkkinä kun kirjoitat raporttiasi. 

Huomaa että jos laitat kuvatiedostot vaikka omaan alihakemistoonsa, Markdown -muotoilussa on oltava suhteellinen polku tiedostoon, esimerkiksi `images/report-sample-image.png`. **Älä** käytä absoluuttisia polkuja `C:\Users\tippaleipa\kurssit\TIRA\kuva.png`, koska nämä eivät tietenkään toimi opettajan koneella. Ei kannata laittaa linkkiä etärepoosikaan, vaan nimenomaan paikalliseen tiedostoon.

Voit myös sisällyttää *lyhyitä* koodinpätkiä vaikkapa Java -formaatilla:

```Java
	@Override
	public int hashCode() {
		// Oma nerokas hajautufunktioni!
	}
```
Tarvittaessa käytä myös paremmin muotoiltuja taulukoita:

| n	| Fill	| Search	| Total |
|-----|--------|--------|-------|
| 500	 | 7	| 700	| 707 |
| 1000 |	9	| 288	| 297 | 

Alaluvut jokaisen tehtävän raportille löydät alta.


## 01-TASK

En ole käynyt ohjelmointi 2 -kurssia, eikä Java ole minulle kielenä ennalta tuttu. Työlästä oli siksi opetella Javan perusteita, mutta hyvien tutoriaalien ja opetusvideoiden (ja luentovideoiden) avulla ei tehtävä tuntunut ollenkaan mahdottomalta; lisäksi Javan rakenteen samankaltaisuus C:n kanssa helpotti paljon. Olio-ohjelmointi on tullut jonkin verran tutuksi kesätöissä, tosin SystemVerilogin parissa.

Tehtävä oli mielestäni melko yksinkertainen, eikä sen suorittaminen tuottanut valtavia haasteita. Eniten aikaa käytin algoritmien toteutuksen miettimiseen ja debuggaukseen. Aikaa vei myös tutustuminen koodaritietokantaan ja sen muodostaviin tiedostoihin.

Mielestäni insertionSort -algoritmin aikakompleksisuusluokka on neliöllinen huonoimmassa tapauksessa. InsertionSortissa joudutaan tekemään satunnainen määrä vertailuja, kun etsitään uudelle oliolle oikeaa paikkaa taulukossa. Kun aineiston koko kasvaa, kasvaa myös todennäköinen vertailujen määrä. Vertailujen määrän kasvaessa yksittäiselle oliolle, kasvaa aikakompleksisuus neliöllisesti.

Reverse-algoritmi taas on mielestäni aikakompleksisuudeltaan lineaarinen. Reversessä vertailua ei tehdä ja olioiden paikan vaihtojen määrä kasvaa samassa suhteessa, kuin aineiston kokokin.

Taulukon kääntäminen kannattaisi tehdä reverse-algoritmilla, jonka aikakompleksisuus on pienempi. Käyttämällä reverse-algoritmia, olioita ei tarvitse vertailla ollenkaan, vaan tehdään ainoastaan puolet aineiston määrästä kertaa paikanvaihtoja.


## 02-TASK

Task 02 oli mielestäni haastavuudeltaan samaa tasoa kuin task 01, eli ei kovinkaan vaikea. Toteutettavat algoritmit sai toteutettua nopeasti edellisen taskin algoritmien pohjalta. Selkeät ohjeet ja demovideo helpottivat uusien rajapintojen, comparatorin ja predicaten käyttöönottoa. Työläintä ja haastavinta oli ymmärtää mikä Comparator on ja miten sitä käytetään, mutta kuten sanottua, ohjeet ja ohjevideo olivat hyvänä apuna. Toisaalta rajapintojen käyttö oli myös opettavaisinta.

Suurella koodarimäärällä (10 000) lajitteleminen koodarinimen mukaisesta järjestyksestä aakkosjärjestykseen koko nimen mukaan vie suurimman ajan (1600-1900ms). Järjestyksen kääntäminen vie joka tilanteessa todella paljon pienemmän ajan (0-1ms). Lajittelu koko nimen mukaisesta aakkosjärjestyksestä koodarinimen mukaiseen vie 450-480 ms. Lajittelu samasta järjestyksestä samaan vei suhteellisen pienen ajan (4-7 ms), mutta pidemmän kuin reverse.

Järjestyksen kääntäminen vie lyhyen ajan, koska siinä vertailua olioiden välillä ei tarvitse tehdä. Reversen aikakompleksisuusluokka on lineaarinen. Kun lajittelu tehdään insertionSortilla reversen sijaan, tapahtuu lajittelussa aina olioiden vertailu, joka vie aikaa. Tästä johtuu ero kahdessa tilanteessa, nopeassa ja hitaassa. Kolmas tilanne on se, jolloin lajittelu tehdään valmiiksi lajitellulle taulukolle. Myös se vie enemmän aikaa kuin reverse, mutta nyt algoritmin tarvitsee verrata jokaista alkiota vaan seuraavaan, eikä niiden paikkoja muuteta.

Yleisesti jos järjestys pitää kääntää, se kannattaisi tehdä reverse-algoritmilla, jonka aikakompleksisuus on pienempi. Käyttämällä reverse-algoritmia, olioita ei tarvitse vertailla ollenkaan, vaan tehdään ainoastaan puolet aineiston määrästä kertaa paikanvaihtoja. Reversen aikakompleksisuus on O(n), kun taas insertionSortilla se on O(n^2).

Add algoritmin aikakompleksisuusluokka on koodin perusteella mielestäni lineaarinen. Algoritmi käy jokaisen lisäyksen yhteydessä koko taulukon läpi tarkistaen, ettei listalla vielä ole lisättävää alkiota. Taulukon koon kasvaessa tarvittavien tarkistusten määrä siis kasvaa samassa suhteessa. Kuvaajasta nähdään kuitenkin täyttöajan kasvun seuraavan toisen asteen yhtälöä, eli se on neliöllinen. Täyttöajan kasvun aikakompleksisuus ei ole sama kuin lisäysalgoritmin. Täytettäessä taulukkoa lisäysalgoritmilla, täytyy jokaisen lisäyksen yhteydessä käydä koko taulukko läpi ja verrata alkoita yksi kerrallaan. Yhden alkion lisäämisen aikakompleksisuus on siis lineaarinen. Kun lisäyksiä tehdään N määrä, joudutaan taulukko käymään läpi N määrä kertoja. Täyttöajan aikakompleksisuudeksi saadaan siis O(n^2) eli se on neliöllinen (worst case).

Täyttöaika suhteessa n:n kokoon

![Täyttöaika suhteessa n:n kokoon](image-4.png)

Hakualgoritmien aikakompleksisuusluokka on lineaarinen O(n). Algoritmeissä käydään taulukko läpi yksi kerrallaan ja tehdään vertailu haettavan alkion ja taulukon alkioiden välillä, kunnes haettu alkio löytyy (tai ei löydy). Alkioiden määrän kasvaessa kasvaa yhteen hakuun tarvittavien vertailujen määrä samassa suhteessa (worst case). Alla oleva kuvaaja kuvaa hakuaikaa suhteessa aineiston kokoon ja siitä voidaan nähdä ajan nousevan lineaarisesti aineiston koon kasvaessa. Big O notaatiolla esitettynä hakualgoritmien aikakompleksisuus on O(f(n)).

Hakuaika suhteessa n:n kokoon

![Hakuaika suhteessa n:n kokoon](image-5.png)



Data:
```
n		Fill	Search	Total
500		15		1357	1372
1000	18		480		498
1500	5		523		528
2000	7		675		682
2500	17		814		831
3000	16		311		327
3500	22		497		519
4000	26		122		148
4500	37		126		163
5000	47		139		186
5500	55		173		228
6000	66		256		322
6500	84		217		301
7000	92		235		327
7500	103		424		527
8000	124		333		457
8500	143		367		510
9000	160		310		470
9500	176		289		465
10000	193		309		502
10500	192		325		517
11000	218		338		556
11500	240		359		599
12000	242		371		613
12500	277		389		666
13000	296		464		760
13500	318		418		736
14000	346		489		835
14500	375		447		822
15000	400		476		876
15500	425		476		901
16000	434		491		925
16500	478		506		984
17000	523		523		1046
17500	563		600		1163
18000	580		548		1128
18500	676		609		1285
19000	635		568 	1203
19500	704		603		1307
20000	717		620		1337
20500	740		634		1374
21000	769		650		1419
21500	815		662		1477
22000	833		677		1510
22500	887		962		1849
23000	932		709		1641
23500	953		732		1685
24000	1017	968		1985
24500	1064	775		1839
25000	985		639		1624
25500	1213	794		2007
26000	1181	843		2024
26500	1232	825		2057
27000	1342	1836	3178
27500	1322	876		2198
28000	1400	877		2277
28500	1442	889		2331
29000	1473	1052	2525
29500	1520	928		2448
30000	1575	861		2436
30500	1650	1051	2701
31000	1704	982		2686
31500	1748	1011	2759
32000	1826	1004	2830
```


## 03-TASK

Mielestäni task 3 oli suunnilleen samaa vaikeustasoa kuin kaksi aiempaa tehtävää, joskin käytin siihen ehkä hieman enemmän aikaa kuin aiempiin. Opin tehtävää tehdessä ja luentovideoiden kautta, mitä ovat iteratiivinen ja rekursiivinen binäärinen haku ja mikä on binäärisen hakumenetelmän aikakompleksisuusluokka. Eniten aikaa käytin "bugin" etsimiseen, jota ei lopulta ollutkaan vaan toteutukseni olikin toimiva. 

Toteutin algoritmeihin sekä rekursiivisen että iteratiivisen haun, toinen on kommenteissa.

Kun haetaan taulukon loppupäästä 50 000 koodarin aineistolla, on hakuaika yleensä n. 30 ms luokkaa. Nopealla haulla hakuaika on 0-1 ms, yleensä 0 ms. Hakuaika nopealla haulla on siis keskimäärin mitätön verrattuna ensimmäiseen hakuun. Listan alkupäästä haettaessa hakuaika lyhenee, esimerkiksi nimellä "Hulmi" haku kestää vain 15 ms, nopea haku koko nimellä 0 ms. Taulukon aivan alkupäästä haettaessa hakuaika on molemmilla hauilla mitätön (0 ms lokin mukaan). Pienillä aineistoilla normaalilla haulla hakuaika lyhenee, esim. 5000 koodarin aineistolla loppupään haku kestää n. 15-19 ms ja nopealla hauöa se on edelleen n. 0 ms.

Nopea haku käyttää puolitushakua, jonka hakuaika on aineiston koosta riippumaton (näin pienillä aineistoilla). Todellisuudessa nopean haun (binäärisen haun) hakuaika on logaritminen, mutta max. 50 000 aineistolla aika on koko ajan 0 ms. Tämä nähdään myös alla olevista taulukoista. Tämän vuoksi haku kestää saman aikaa alkion sijainnista riippumatta, oli se sitten alkupäässä, loppupäässä tai keskellä listaa.


![Hakuaika (ascending)](image-8.png)
![Hakuaika (descending)](image-11.png)

Hitaalla hakualgoritmilla hakuaika kasvaa lineaarisesti siirryttäessä listan alkupäästä loppupäähän. Tämä johtuu siitä, että hidas haku tekee vertailuoperaatiot alkioiden välillä listan alusta loppuun tai haettuun alkioon saakka. Näin ollen tarvittavien vertailujen määrä kasvaa lineaarisesti siirryttäessä listalla alkupäästä kohti loppua. Lineaarisen hakualgoritmin aikakompleksisuusluokka on O(n).

Alla olevat kaaviot kuvaavat täyttöaikoja ja lajitteluaikoja testin tuottaman datan perusteella. Lajitteluaikojen ja täyttöaikojen kuvaajat mukailevat edellisen tehtävän vastaavia kuvaajia, sillä aineiston lajittelu ja täyttö tehtiin nyt samalla algoritmillä kuin edellisessä tehtävässä.

![Alt text](image-6.png)

![Alt text](image-7.png)

![Alt text](image-9.png)

![Alt text](image-10.png)

Ero edelliseen tehtävään nähdään hakuaikojen kuvaajissa. Nyt hakuajan kuvaajat näyttävät aineiston koon olevan merkityksetön hakuaikaan. Todellisuudessa puolitushaun hakuaika ei kuitenkaan ole aineiston koosta riippumaton. Koodia analysoidessa nähdään, että haettavan aineiston määrä puolittuu jokaisella iteraatiolla. Tästä voidaan edelleen päätellä, että aineiston koon kaksinkertaistuassa tarvittavien iteraatioiden (vertailujen) määrä kasvaa yhdellä. Näin ollen binäärinen hakualgoritmi on aikakompleksisuudeltaan logaritminen O(log(n)). Tämän vuoksi binäärinen hakualgoritmi on tehokas suurillakin aineistomäärillä.


## 04-TASK

Tehtävä opetti minulle, miten stack -tietorakenne toteutetaan JAVA:lla. Lisäksi opin string-tietotyypin käsittelystä javalla. Eniten haasteita tuotti alkuun pääseminen, mutta demovideo auttoi siinä. Lisäksi haastavana koin ParenthesisChecker-osion.

Toteutukseni vastaa tehtävänannossa määriteltyjä aikakompleksisuusvaatimuksia, sillä stackin metodien suoritusaikaan eivät vaikuta parametrit vaan suoritusaika on vakio eli vaatimusten mukainen O(1). toString metodin aikakompleksisuus on O(n), sillä siinä suoritusajan määrittää annetun taulukon pituus. Taulukon alkioiden määrän kasvattaminen yhdellä lisää for-silmukkaan yhden kierroksen. Aika kasvaa siis lineaarisesti. Reallokointi on aikakompleksisuudeltaan lineaarinen (O(n)), sillä myös siinä käydään taulukon alkiot kerran läpi for-silmukalla ja jälleen taulukon alkioiden määrän kasvattaminen kasvattaa tarvittavien toistojen määrää samassa suhteessa. Näin ollen myös push-operaatio on lineaarinen, kun joudutaan tekemään reallokointi.

Jos lainausmerkit tekstissä ovat väärin, ei algoritmin sulkujenlaskenta toimi. Esimerkiksi jos teksti alkaa lainausmerkillä, eikä toista lainausmerkkiä tule, ei algoritmi laske tekstistä ollenkaan sulkuja. Algoritmi on silti oikeellinen, sillä se ei jumiudu ja suorittaa tehtävän annetun syötteen mukaan loppuun saakka. Tällöin virhe on syötteessä, ei algoritmissä.


## 05-TASK

Tehtävä opetti minulle jono-tietorakenteen luomisen javalla. Jonon toimintaperiaate oli minulle jo ennestään tuttu, joten sen opettelemiseen ei tarvinnut käyttää aikaa. Haasteita tuotti reallocate ja toString -metodit, joita en meinannut ensin saada menemään testeistä läpi (erityisesti printTest()). Lopulta kuitenkin pitkän debuggauksen jälkeen löysin ongelman (headin ja tailin resetointi puuttui clear() -metodissa) ja sain sen ratkaistua.

Linkitettyyn listaan voidaan lisätä ja poistaa solmuja dynaamisesti tarpeen mukaan. Tämän vuoksi se voi olla muistikompleksisuudeltaan tehokkaampi kuin jono erityisesti silloin, kun ei tiedetä ennalta kuinka paljon tilaa tarvitaan. Toisaalta jos tarvittava muisti on tiedossa jo ennalta, voidaan taulukko luoda suoraan oikeaan kokoon ja tällöin se voittaa linkitetyn listan muistikompleksisuudessa.

Linkitetyssä listassa alkioiden lisääminen ja poistaminen on aikakompleksisuuden kannalta tehokasta missä tahansa kohden listaa. Taulukkopohjaisessa toteutuksessa lisättäessä keskelle listaa joudutaan tekemään tiedonsiirtoa, joka ei ole aikatehokasta. Linkitetty lista voittaa siis taulukkopohjaisen ratkaisun, kun lisäyksiä tai poistoja tehdään listan keskeltä, ja taulukkopohjainen kun operoidaan taulukon reunoilla, eli esimerkiksi tässä tehtävässä toteutettu FIFO.

Toteutukseni vastaa tehtävänannossa määriteltyjä aikakompleksisuusvaatimuksia, sillä jonon metodien suoritusaikaan eivät vaikuta parametrit vaan suoritusaika on vakio eli vaatimusten mukainen O(1). toString metodin aikakompleksisuus on O(n), sillä siinä suoritusajan määrittää annetun taulukon pituus. Taulukon alkioiden määrän kasvattaminen yhdellä lisää while-silmukkaan yhden kierroksen. Aika kasvaa siis lineaarisesti. Reallokointi on aikakompleksisuudeltaan lineaarinen (O(n)), sillä myös siinä käydään taulukon alkiot kerran läpi while-loopilla ja jälleen alkioiden määrän kasvattaminen kasvattaa tarvittavien toistojen määrää samassa suhteessa. Näin ollen myös lisäys-operaatio on lineaarinen, kun joudutaan tekemään reallokointi.


## 06-TASK

Tehtävä oli mielestäni tähänastisista haastavin. Lajittelualgoritmien toimintaperiaatteiden sisäistämiseen meni jonkin aikaa, mutta luentovideot ja Youtube-tutoriaalit auttoivat. Lisäksi algoritmien havainnollistaminen kynän ja paperin avulla helpottivat ymmärtämistä. Kaikista eniten minulle tuotti vaikeuksia mergesort, jota en edelleenkään saanut menemään testeistä läpi. Yleisesti haasteita testien kanssa oli eniten indeksien kanssa. Yleinen virhe testistä oli "index 100 out of range with size 100", vaikka mielestäni toteutukseni pitäisi toimia oikein. Tähän jäin myös mergesortin kanssa. Lisäksi ongelmaa oli kekomuistin ylivuodon kanssa, johon lopulta mikään kurssilla esitetyistä ratkaisuvaihtoehdoista ei auttanut. Sain siis nopean algoritmin testit ajettua vain maksimissaan 1 000 000 kokoisella aineistolla (2 000 000 jäi suoriutumatta) ja suoritin analyysit näiden tulosten perusteella.

Toteutin tehtävässä heapsort, quicksort ja mergesort -algoritmit.

Hitaan ja nopean lajittelualgoritmin nopeusero ei näy vielä kaikista pienimmällä aineistolla (n = 100), jossa hidas algoritmi suoriutui jopa paremmin kuin heapsort (insertionsort 6 ms, heapsort 7 ms, quicksort 4 ms ja ). Kun aineistoa kasvatetaan, alkavat erot näkyä heti selvästi ja kasvavat valtavan suureksi lajiteltaessa suurempia aineistoja (suurimpia aineistoja ei saanut lajiteltua insertionsortilla järkevässä ajassa). Tätä havainnollistavat alla olevat kuvaajat, joissa näkyvät sekä kunkin algoritmin lajitteluaika suhteessa aineiston kokoon, että yksittäisen elementin lajitteluaika suhteessa aineiston kokoon. Kuvaajista ja datasta nähdään, että nopeilla algoritmeilla pienen aineiston (n = 100) yksittäisen alkion lajittelu vei kaikista suurimman ajan. Kun aineiston kokoa kasvatetaan, ei lajitteluaika per alkio juurikaan muutu ja erittäin suurikin (1 000 000) ainesto saadaan lajiteltua tehokkaasti. Nopeiden algoritmien pidempi lajitteluaika pienillä aineistokoilla verrattuna hitaaseen algoritmiin voi selittyä niiden monimutkaisemmalla rakenteella. Insertionsortissa käydään taulukko läpi yhdellä silmukalla, joka on hyvin pienillä datamäärillä  tehokasta. Kaikissa toteuttamissani nopeissa algoritmeissa hyödynnetään rakenteessa erillisiä metodeja, sekä tehdään useampia vertailuja ja muistinvarauksia eri muuttujille. Kaikki tämä "ylimääräinen" toiminta vie aikaa, ja näkyy siksi hitautena kaikkein pienimmillä aineistoilla.

Kaavioiden ja testidatan perusteella nopeiden algoritmien aikakompleksisuusluokka näyttäisi olevan lineaarinen, sillä yksittäisen alkion lajitteluaika on kutakuinkin vakio. Tuloksista kuitenkin puuuttuu 2 000 000 alkion aineisto, joten lajitteluajan todellista kasvua erittäin suurilla aineistoilla ei tulosten perusteella nähdä. Teorian mukainen aikakompleksisuus quicksortille on keskimäärin O(n*log(n)) ja huonoimmassa tapauksessa O(n^2).Toteutukseni vastaa mielestäni tätä aikakompleksisuutta. Huonoimpaan tapaukseen joudutaan, jos taulukko on jo lajiteltu, jolloin pivot-arvoksi valikoituu taulukon suurin tai pienin arvo.

Sekä heapsortin että mergesortin aikakompleksisuusluokka on teorian mukaan O(n*log(n)), sekä keskimäärin että huonoimmassa tapauksessa. Tämä johtuu siitä, ettei niiden lajittelunopeuteen vaikuta taulukon alkuperäinen järjestys. Näin ollen lajitteluaika on aina sama.

Kokonaisuudessaan nopein algoritmi näyttää testidatan ja kaavioiden perusteella olevan quicksort, toiseksi nopein heapsort ja kolmantena mergesort. Keskiarvot yksittäisen elementin lajitteluajoille olivat: quicksort 0.0091 ms/element, heapsort 0.014 ms/element ja mergesort 0.018 ms/element. Datan ja kuvaajien (kuvat 1, 2, 3 ja 4) perusteella mergesort suoriutui pienistä aineistokoista hitaammin kuin muut algoritmit. Kuitenkin suurimmalla aineistolla (n = 1 000 000) mergesort oli nopein 4245 ms ajalla. Quicksort suoriutui tästä toiseksi nopeiten ajalla 5279 ms ja heapsort ajalla 8071 ms. Erot algoritmien suoritusajoissa eivät kuitenkaan ole kovinkaan suuret. Kuvassa 4 on vielä esitettynä vertailu nopeiden algoritmien lajitteluajoista eri suuruisilla aineistoilla.

Kuten raportissa on aiemmin analysoitu ja todettu, insertionsortin aikakompleksisuusluokka on O(n^2). Tämän vuoksi se on testidatan perusteella selkeästi hitain algoritmi.

Kuva 1. Insertion sort kaaviot

![Kuva 1. Insertion sort kaaviot](image-12.png)

Kuva 2. HeapSort kaaviot

![Kuva 2. HeapSort kaaviot](image-13.png)

Kuva 3. QuickSort kaaviot

![Kuva 3. QuickSort kaaviot](image-14.png)

Kuva 4. Mergesort kaaviot

![Kuva 4. Mergesort kaaviot](image-16.png)

Kuva 5. Quicksort vs Heapsort

![Kuva 5. Quicksort vs Heapsort](image-15.png)

Kuva 6. Nopeiden algoritmien vertailu

![Kuva 6. Nopeiden algoritmien vertailu](image-18.png)


Testituloksia taulukoituna:

Hidas (insertionsort):
```
Test#   Count   ms      ms/element
1		100		6		0,06
2		1000	21		0,021
3		5000	392		0,078
4		10000	1416	0,142
5		50000	110361	2,207
6		100000	506958	5,07
```

Heapsort:	
```
Test#	Count	ms		ms/element
1		100		7		0,07
2		1000	5		0,005
3		5000	18		0,004
4		10000	51		0,005
5		50000	268		0,005
6		100000	449		0,004
7		1000000	8071	0,008
```

Quicksort:
```
Test#	Count	ms     	ms/element
1		100		4		0,04
2		1000	7		0,007
3		5000	22		0,004
4		10000	22		0,002
5		50000	132		0,003
6		100000	335		0,003
7		1000000	5279	0,005
```

Mergesort:
```
Test#	Count	 ms     ms/element
  1	    100	     10	    0,100
  2	    1000	 8	    0,008
  3	    5000     28	    0,006
  4	   	10000	 34	    0,003
  5	   	50000	 147	0,003
  6	  	100000	 343	0,003
  7	 	1000000	 4245	0,004
  ```



## 07-TASK

Tehtävä 7 oli mielestäni selkesästi tähänastisista kurssin tehtävistä haastavin. Toteutin indexOf() ja getIndex() -metodit D-toteutuksella, ja niiden saaminen toimimaan oikein oli mielestäni kaikista haasteellista. Tein algoritmit ennen niistä kertovan liveluennon ja ohjeiden julkaisemista, joten jouduin painimaan ongelmieni kanssa itsekseni. Sain kuitenkin lopulta kaiken toimimaan niin kuin pitääkin. Binäärisen hakupuun rakenne ja toiminta tuli tehtävää tehdessä hyvin tutuksi.

Metodien aikakompleksisuus:

Binäärinen hakupuu käyttää toteutuksessani metodeissa privaatteja apumetodeja. Metodit getIndex() ja indexOf() käyttävät vastaavaa logiikkaa hyödyntäviä apumetodeja, joissa haluttu indeksi etsitään käymällä puuta läpi ylhäältä alas, valiten aina oikea haara sen indeksin arvon (indexOf()) tai avaimen arvon (getIndex()) mukaan. Metodit hyödyntävät indeksien laskennassa solmuissa olevaa tietoa niiden lasten lukumäärästä, jolloin kaikkia solmuja ei tarvitse käydä läpi indeksien laskemista varten. Tämän toteutuksen aikakompleksisuus on parhaassa ja keskiarvoisessa tapauksessa O(log(n)) tasapainotetulle hakupuulle. Huonoimpaan tapaukseen päästään, kun hakupuu on linkitetty lista. Tällöin joudutaan huonoimmassa tapauksessa käymään läpi listan jokainen alkio ja näin ollen aikakompleksisuus on O(n).

Puuhun lisääminen tehdään iteratiivisesti apumetodin addNode() avulla. addNode() lisää arvon avaimen arvon mukaiseen paikkaan puussa tehden vertailun jokaisessa haarassa ja valiten oikean haaran avaimen mukaan. Vertailujen määrä määrittyy puun syvyyden mukaan, ja tasapainotetun puun aikakompleksisuus tälle metodille on logaritminen (O(log(n))). Huonoimmassa tapauksessa (linkitetty lista) joudutaan jälleen käymään puun jokainen alkio läpi ja aikakompleksisuus on lineaarinen (O(n)).

Puusta etsiminen predikaatin avulla  tehdään rekursiivisesti apumetodeilla searchIndex() (metodissa findIndex()) ja findNodeValue() (metodissa find()). Apumetodit searchIndex ja findNodeValue lisäävät listan alkiot yksitellen indeksien mukaiseen järjestykseen pinotietorakenteeseen ja vertaavat niiden arvoja indeksin mukaisessa järjestyksessä predikaattiin. Näin ollen niiden molempien aikakompleksisuus on alkioiden määrästä riippuvainen eli lineaarinen O(n).

Metodi size() käyttää apumetodia sizePartTree(), joka laskee solmun lasten lukumäärään perustuen puun koon annetusta solmusta alaspäin. Metodi perustuu yksinkertaiseen arvon hakemiseen, ja sen aikakompleksisuus ei riipu alkioiden määrästä ja on siten vakiollinen O(1).

Metodi toArray() käyttää apumetodia BSTToArray(), joka käy hakupuun läpi rekursiivisesti ja lisää alkiot indeksien mukaiseen järjestykseen taulukkoon. Riippumatta puun muodosta joudutaan sen jokainen alkio käydä aina läpi, jolloin aikakompleksisuus on lineaarinen O(n).

Kaikki toteuttamani algoritmit ovat oikeellisia, sillä ne  suorittavat niille annetut tehtävät halutulla tavalla, suoriutuvat kaikilla syötteillä loppuun saakka ja niiden suoritus päättyy, kun haluttuun lopputulokseen on päästy.

Puun maksimisyvyys eri aineistokoilla oli toteutuksellani seuraava:

```
100 koodaria: 12
1 000 koodaria: 21
5 000 koodaria: 28
10 000 koodaria: 29
50 000 koodaria: 39
100 000 koodaria: 37
1 000 000 koodaria: 50

Tasapainotetun puun syvyys eri aineistokoilla taas olisi:

100 koodaria: 7
1 000 koodaria: 10
5 000 koodaria: 13
10 000 koodaria: 14
50 000 koodaria: 16
100 000 koodaria: 17
1 000 000 koodaria: 20
```

Nähdään, että toteutukseni luomat puut ovat huomattavan paljon syvempiä, kuin tasapainotettu puu olisi (optimaalinen tilanne). Tämän johdosta algoritmien aikakompleksisuudet eivät saavuta testidatan mukaan niiden teorian mukaista keskimääräistä aikakompleksisuutta.

Kuvissa 1-4 nähdään testien tuloksia binäärisen hakupuun ja simplecontainerin suorituskyvystä eri datamäärillä. Kuvassa 1 on lisäysaika, joka vaikuttaisi olevan simplecontainerin osalta teorian mukainen, eli neliöllinen. BST:llä lisäämisen aikakompleksisuus on kuvaajan mukaan lähestulkoon lineaarinen, vaikka teorian mukaan sen pitäisi olla parhaassa tapauksessa logaritminen. Tämä aiheutuu todennäiköisesti siitä, ettei algoritmin luoma puu ole ideaali eli tasapainotettu.

Kuvassa 2 on tiedon vieminen taulukkoon. Jälleen BST:llä käyrä näyttäisi olevan lähellä lineaarista, kun taas simplecontainerilla se on neliöllinen. Nyt aikakompleksisuus on teorian mukainen, sillä puun muoto ei vaikuta toArray():n suoritusaikaan, vaan siinä käydään joka tapauksessa jokainen puun alkio läpi.

Kuvassa 3 nähdään hakuaika, joka on molemmilla tietorakenteilla lineaarinen. Myös hakuaika on teorian mukainen, sillä tässäkin tapauksessa puu käydään läpi alkio kerrallaan sen muodosta riippumatta.

Kuva 4 havainnollistaa getIndex() aikaa, eli aikaa joka kuluu jokaisen alkion hakemiseen indeksin avulla. Binäärisellä hakupuulla getIndex:n käyrä näyttäisi muotoutuvan kohti logaritmista käyrää. Puun muodon poikkeaminen tasapainotetusta aiheuttaa sen, että erityisesti aineiston koon kasvaessa aikakompleksisuus poikkeaa logaritmisesta käyrästä. Simplecontainer suoriutuu tästä testistä paremmin, eikä getIndex:n käyttämä aika näytä testidatan perusteella muuttuvan aineiston koon mukaan.

Kuvaajista nähdään, että BST suoriutuu aineiston lisäämisestä huomattavan paljon tehokkaammin kuin simplecontainer. Simplecontainerin kohdalla viimeinen kohtuullisessa ajassa saatu aineistokoko oli 100 000 alkiota, kun taas BST:llä 1 000 000 alkion aineisto saatiin lisättyä 2597 millisekunnissa. 2 000 000 alkion aineistolla koneestani loppui muisti kesken, joten sitä en voinut suorittaa.

Vastaava tulos saadaan toArray() metodille; simplecontainerilla suoritusaika 100 000 aineistolle on 119 188 ms kun taas bst:llä se on 11 ms. 1 000 000 alkion aineiston BST lisäsi taulukkoon 98 millisekunnissa.

Hakuaika näyttäisi olevan molemmissa tietorakenteissa melko lähellä toisiaan 100 000 alkion aineistoon saakka (suurempaa ei saatu testattua simplecontainerilla), mutta simplecontainer voittaa testiaineiston mukaan muutamilla millisekunneilla.

getIndex():n hakuaika on pienillä aineistoilla molemmilla tietorakenteilla lähes mitätön. 50 000 alkion aineistolla simplecontainer suoritti haun testin mukaan ajassa 4 ms, kun taas BST:llä siihen kului 12 ms ja 100 000 alkion aineistolla simplecontainer 0ms, BST 16 ms. Näiden tulosten mukaan taulukkopohjainen ratkaisu on paljon tehokkaampi indeksillä haettaessa. Binäärisessä hakupuussa indeksejä ei ole valmiina, vaan ne täytyy laskea puun solmuille erillisen metodin avulla. Omassa tietorakenteessani toteutin haun indeksin mukaan metodilla, jonka teoreettinen aikakompleksisuus on logaritminen. Taulukkopohkaisessa tietorakenteessa taulukko on indeksöity valmiiksi, jolloin tietyllä indeksillä voidaan osoittaa suoraan taulukon arvoon. Tämän vuoksi simplecontainer-toteutuksessa indeksin mukaan hakeminen suoritetaan vakioajassa ja tehokkaammin, kuin binäärisen hakupuun tapauksessa.

Aikatehokkuudesta (aika/alkio eri algoritmeissa) en saanut Excelin avulla tuotettua järkevää kaaviota, mutta testidata on esitetty kuvassa 6. Datan perusteella nähdään lisäysajan kasvun yksittäiselle elementille olevan BST:n tapauksessa lineaarista ja simplecontainerilla neliöllistä. Lisäyksen aikatehokkuus on siis BST:llä hyvä suurillakin aineistoilla, mutta simplecontainerilla suurilla aineistoilla todella huono. Hakuaika getIndex:llä näyttää datan perusteella olevan yksittäiselle alkiolle melko lailla vakio molemmilla tietorakenteilla, eli tässä tapauksessa saavutettiin hyvä aikatehokkuus.

Eri toimintojen suoritusaikoja käyttöliittymän lokista on esitetty kuvassa 5. Käyttöliittymällä kaikki sujuu nopeasti ja tehokkaasti alle 1 000 000 suuruisilla aineistoilla. 1 000 000 alkion aineiston tuominen ja lajittelu TiraCodersiin vei binäärisellä hakupuulla melko pitkän ajan, 11 929 ms. Tämä on kuitenkin mielestäni kohtuullinen aika näin suurelle aineistolle. Kun aineisto saatiin tuotua käyttöliittymään, sen selaaminen ja siitä hakeminen onnistui sujuvasti ja ongelmitta. Pisin hakuaika 1 000 000 alkion aineistolla oli 94 ms, mutta useimmat haut onnistuivat paljon nopeammin. Aineiston uudelleenjärjestäminen näin isolla aineistolla vei paljon aikaa. 

Kuva 1. Lisäysajat

![Kuva 1. Lisäysajat](image-20.png)

Kuva 2. toArray()

![Kuva 2. toArray()](image-21.png)

Kuva 3. Hakuajat

![Kuva 3. Hakuajat](image-22.png)

Kuva 4. getIndex()

![Kuva 4. getIndex()](image-23.png)

Kuva 5. Käyttöliittymän loki

![Kuva 5. Käyttöliittymän loki](image-19.png)

Kuva 6. Testidata taulukossa

![Kuva 6. Testidata taulukossa](image-24.png)

## 08-TASK

Tehtävä 08 oli mielestäni helpompi kuin 07, mutta debuggaus vaati melko paljon työtä. Mikään toteutuksen vaiheista ei ollut erityisen haastava, mutta haastavinta oli toteutuksen debuggaus ja pienten virheiden löytäminen, sillä testit eivät selkeästi osoittaneet kaikkien virheiden lähdettä. Lopulta sain kuitenkin toteutuksen toimivaksi. Opin tehtävässä hajautustaulun toimintaperiaatteen ja toteutuksen javalla.

Mielestäni toteutukseni hajautustaululle on oikeellinen, sillä se suoriutuu loppuun ja päätyy oikeaan lopputulemaan kaikilla annetuilla syötteillä. 

Hajautustaulun aikakompleksisuusanalyysi:

Hajautustauluun lisääminen on hyvällä hajautusalgoritmillä parhaassa tapauksessa vakiollinen O(1) ja keskimääräisessä tapauksessa käytännössä vakiollinen. Hyvällä hajautusfunktiolla törmäyksiä tapahtuu vain vähän, ja näin ollen lisäysaikaan ei juurikaan vaikuta lisättävän aineiston koko. Pahimmassa tapauksessa (huono hajautusfunktio) lisäysaika on lineaarinen, jolloin törmäysten määrä kasvaa lineaarisesti ja näin ollen myös lisäysaika kasvaa törmäysten kasvun mukaan.

get()-operaation aikakompleksisuus on sama kuin lisäyksen (O(1) hyvällä hajautusfunktiolla), sillä hakiessa indeksi lasketaan samaan tapaan kuin lisättäessäkin.

find()-operaation aikakompleksisuus on lineaarinen O(n). Hakiessa predikaatilla taulukkoa käydään järjestyksessä läpi, kunnes haluttu arvo löydetään, ja näin ollen hakuaika seuraa aineiston määrää.

Tilan allokoiminen (reallocate()) on aikakompleksisuudeltaan lineaarinen O(n). Allokoiminen vaatii taulukossa olevien elementtien uudelleenhajautusta, jolloin suoritusaika riippuu taulukon koosta. 

Mikäli hajautustaulu sisältää jo alkioita, ensureCapacity() käyttää reallocate()-metodia ja näin ollen sen aikakompleksisuus on vastaavasti lineaarinen. Jos taas taulu ei sisällä alkioita, on ensureCapacity:n aikakompleksisuus vakiollinen.

Operaatiot size(), capacity() ja clear() ovat kaikki aikakompleksisuudeltaan vakiollisia, sillä ne ovat toiminnaltaan hyvin yksinkertaisia eivätkä sisällä looppeja.

toArray() on lineaarinen, sillä siinä käydään taulukko läpi ja siirretään kaikki sen elementit yksi kerrallaan uuteen taulukkoon. Taulukon koko siis ratkaisee, kauanko suoritus kestää.

Mittaustulosten ja niistä muodostettujen käyrien (käyrät esitetty alla) mukaan hajautustaulun aikakompleksisuus on lähestulkoon lineaarinen. Tämä aiheutuu epätäydellisen hajautusfunktion aiheuttamasta törmäysten määrän kasvusta. Törmäysten käsittely tehdään probing-menetelmällä, jossa törmäyksen sattuessa lasketaan uusi hash lisättävälle alkiolle hashModifier-muuttujan avulla. Näin tehdään, kunnes taulukosta löydetään vapaa paikka.

Mittaustulosten mukaan hajautustauluun lisääminen oli nopeampaa kuin binääriseen hakupuuhun (50 000 alkion aineistolla hajautustaululla 64 ms, BST:llä 111 ms). Teorian mukainen lisäämisen aikakompleksisuus on BST:llä logaritminen ja hajautustaululla vakiollinen (tässä lähes lineaarinen törmäysten johdosta). Tämän vuoksi hajautustauluun lisääminen tapahtuu nopeammin kuin BST:hen.

Hakeminen hajautustaulusta on sekä teorian, että tulosten mukaan aikakompleksisuudeltaan lineaarista, kun taas hakeminen BST:stä logaritmista. 1000 alkion aineistolla hajautustaulun hakuaika on 2ms, BST:llä 1ms ja 50 000 alkion aineistolla hajautustaululla 21ms, BST:llä 59 ms. Koska hajautustaulua ei testata suuremmalla aineistolla kuin 50 000, hakuaikoja ei voida vertailla suurilla aineistoilla. Tulosten mukaan pienemmillä aineistoilla kuin 100 000, on hajautustaulu tehokkaampi. Hajautustaulun hakuaika riippuu hajautusfunktiosta, sillä hyvällä hajautusfunktiolla törmäyksiä tapahtuu vähän, ja hakuajassa päästään lähelle vakiollista.

toArray (sorted) on binäärisellä hakupuulla selvästi tehokkaampi kuin hajautustaululla. Hajautustaululla alkiot eivät ole tietosäiliössä valmiissa järjestyksessä, joten taulukko joudutaan lajittelemaan erillisellä lajittelualgoritmilla, kun taas BST:ssä alkiot ovat valmiiksi järjestyksessä ja toArray lisää ne taulukkoon tässä järjestyksessä.

SimpleContainerilla suurin testattu aineisto oli 50 000 alkiota. Hajautustaulu voittaa simpleContainerin lisäysajassa helposti aineiston koon kasvaessa 5000 alkioon ja siitä yli. Tätä pienemmillä aineistoilla lisäysajat ovat suhteellisen lähellä toisiaan. Hakuajat ovat hajautustaulutoteutuksellani hiukan paremmat kuin simpleContainerilla. Myös toArray:n tulokset ovat samankaltaiset, joidenkin millisekuntien heitoilla. 

Lisäysaika simpleKeyedContainerilla on selvästi nopeampi kuin hajautustaulutoteutuksellani aineistokoosta riippumatta. Hakuaika taas kasvaa simpleKeyedContainerilla neliöllisesti, kun taas hajautustaulutoteutuksellani kutakuinkin lineaarisesti. Näin ollen hajautustaulu voittaa simpleKeyedContainerin hakuajassa selvästi, etenkin suurilla aineistoilla. toArray:ssä ajat ovat lähellä toisiaan.

Eri tietosäilioiden suorituskykymittausten tuloksia: 

Hashtable:
![Hashtable performance](image-31.png)

BST:
![BST](image-32.png)

SimpleContainer:
![SimpleContainer](image-33.png)

SimpleKeyedContainer:
![SimpleKeyedContainer](image-34.png)

Sanojen laskeminen käyttöliittymän kautta tapahtuu sujuvasti ja nopeasti. Suurin aineisto, jolla tämän tein, oli tämän projektin kansio. Viivettä ei juurikaan tullut, ja lista tulostui nopeasti.

Testattuja hashfunktioita ja niiden suorityskykyjä:

Ensimmäinen funtio:
![Ensimmäinen](image-25.png)
![Ensimmäisen tulokset](image-26.png)

Toinen funktio:
![Toinen](image-27.png)
![Toisen tulokset](image-28.png)

Lopullinen funktio:
![Lopullinen](image-29.png)
![Lopullisen tulokset](image-30.png)



## 09-TASK