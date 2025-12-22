// Annab kasutaja kaebuste põhjal tervisenõu
class HealthAdvice {
    private User user;

    public HealthAdvice(User user) {
        this.user = user;
    }

    public String giveAdvice(String kaebus) throws UniversalUserException {
        if (kaebus == null || kaebus.trim().isEmpty()) {
            throw new UniversalUserException("Sisesta kaebus!");
        }

        String advice = "";
        String kaebusLower = kaebus.toLowerCase();

        if (kaebusLower.contains("energ") || kaebusLower.contains("väsi") || kaebusLower.contains("nõrk")) {
            advice += "Madal energiatase, väsimus ja nõrkusetunne võivad tulla erinevatest põhjustest. Põhjuseks võib olla ";
            if (user.getGoal() == 1) {
                advice += "liiga suur kaloripuudujääk. Kontrolli oma kaloreid! ";
            }
            if (user.getGender().equalsIgnoreCase("N")) {
                advice += "rauavaesus. Söö rohkem punast liha, kaunvilju ja tumerohelisi lehtköögivilju. ";
            } else {
                advice += "liiga vähe und. Proovi magada 7-9 tundi öösel. ";
            }
            if (user.getActivityLevel() >= 4) {
                advice += "ületreenimine. Võta taastumispäev! ";
            }

        } else if (kaebusLower.contains("uni") || kaebusLower.contains("maga") || kaebusLower.contains("unetus")) {
            advice = "Uneprobleemide lahendamiseks: ";
            advice += "hoia magamistoas jahe temperatuur (16-19°C). ";
            if (user.getActivityLevel() == 5) {
                advice += "Proovi vältida kõrget aktiivsust paar tundi enne magamaminekut. ";
                advice += "Väldi kofeiini pärast kella 14. ";
            } else if (user.getActivityLevel() == 1) {
                advice += "Väldi ekraane 1-2 tundi enne magamaminekut. ";
                advice += "Proovi tõsta oma aktiivsust. Liiga madal aktiivsus negatiivselt mõjutab unekvaliteeti. ";
            }
            if (user.getAge() < 25) {
                advice += "Loo kindel unerütm - mine magama ja ärkad samal ajal iga päev. ";
            }

        } else if (kaebusLower.contains("kaal") || kaebusLower.contains("rasv")) {
            advice = "Aeglane rasvakaotus või üldse mitte kaalulangus ei tähenda tavaliselt, et sa teed midagi valesti. ";
            double bmi = user.calculateBMI();
            if (bmi < 18.5) {
                advice += "BMI on liiga madal. Liiga madalad kalorid = stress + aeglasem rasvakaotus. ";
                advice += "Keskendu valgurikkale toidule ja jõutreeningule. ";
            } else if (bmi > 18.5 && bmi <= 25){
                advice += "Oled tegelikult tervislikus kaalus. ";
                advice += "Veendu, et sööd iga toidukorraga valku. ";
                advice += "Keskendu aktiivsele eluviisile ja lihasmassi kasvatamisele. ";

            }
            else if (bmi > 25) {
                advice += "Loo mõõdukas kaloripuudujääk (300-500 kcal päevas). Kui kaal ei muutu, defitsiiti pole või see on liiga väike. ";
                advice += "Lisa kardio- ja jõutreeningut 3-4 korda nädalas. ";
                if (user.getAge() > 40) {
                    advice += "Pärast 40. eluaastat aeglustub metabolism - ole kannatlik! ";
                }
            }

        } else if (kaebusLower.contains("tre") || kaebusLower.contains("motivatsioon") || kaebusLower.contains("sport")) {
            advice = "Treeningu motivatsiooni suurendamiseks ";
            advice += " pea treeningpäevikut edusammude jälgimiseks. ";
            if (user.getActivityLevel() == 1) {
                advice += "Kui ei ole varem spordiga tegelenud, kaalu gruppitreeningutes osalemist. ";
                advice += "Alusta väikestest eesmärkidest - näiteks 10 minuti jalutuskäik päevas. ";
            }
            if (user.getGoal() == 3) {
                advice += "Lihasmassi kasvatamiseks keskendu jõutreeningule 4-5 korda nädalas. ";
            }

        } else if (kaebusLower.contains("stress") || kaebusLower.contains("ärevus") || kaebusLower.contains("pinge")) {
            advice = "Stressi vähendamiseks: ";
            advice += "veendu, et saad piisavalt und (7-9 tundi).";
            advice += "Kui stress on püsiv, otsi professionaalset abi.";
            if (user.getActivityLevel() == 1 || user.getActivityLevel() == 2) {
                advice += "Loo harjumus teha regulaarset trenni - liikumine vähendab stresshormoone. ";
            }
            advice += "Proovi hingamisharjutusi või meditatsiooni 10 minutit päevas. ";

        } else if (kaebusLower.contains("valu") || kaebusLower.contains("vigastus") || kaebusLower.contains("haige")) {
            advice = "Kindlasti külasta arsti! Äppi kasutamine ei asenda arsti külastamist! ";
            if (user.getActivityLevel() >= 4) {
                advice += "Kui valu on seotud treeninguga, võta puhkepäev ja konsulteeri füsioterapeudiga. ";
            }
        } else {
            return "Sisesta kaebus järgmiste probleemide korral: energiapuudus, väsimus, unetus, kaalulangetus, trenn, motivatsioon, stress, vigastus või valu.";
        }

        return advice;
    }
}