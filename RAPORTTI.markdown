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

## 03-TASK

## 04-TASK

## 05-TASK

## 06-TASK

## 07-TASK

## 08-TASK

## 09-TASK