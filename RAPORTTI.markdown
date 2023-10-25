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
![Täyttöaika suhteessa n:n kokoon](image-4.png)

Hakualgoritmien aikakompleksisuusluokka on lineaarinen O(n). Algoritmeissä käydään taulukko läpi yksi kerrallaan ja tehdään vertailu haettavan alkion ja taulukon alkioiden välillä, kunnes haettu alkio löytyy (tai ei löydy). Alkioiden määrän kasvaessa kasvaa yhteen hakuun tarvittavien vertailujen määrä samassa suhteessa (worst case). Alla oleva kuvaaja kuvaa hakuaikaa suhteessa aineiston kokoon ja siitä voidaan nähdä ajan nousevan lineaarisesti aineiston koon kasvaessa. Big O notaatiolla esitettynä hakualgoritmien aikakompleksisuus on O(f(n)).
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

## 07-TASK

## 08-TASK

## 09-TASK