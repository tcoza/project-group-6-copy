package ca.mcgill.ecse321.tutoringapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class StudentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);
        getSupportActionBar().setTitle("Students");

        TableLayout table = findViewById(R.id.table);
        for (String[] user : users)
            addStudent(table, truncate(user[0], 15), truncate(user[1], 10), truncate(user[2], 10), true);
    }

    private void addStudent(TableLayout table, String username, String first, String last, boolean active)
    {
        TableRow row = new TableRow(this);
        for (String data : new String[]{username, first, last})
        {
            TextView text = new TextView(this);
            text.setTextSize(16);
            text.setText(data);
            text.setPadding(0,15,0,0);
            row.addView(text);
        }
        CheckBox activeCheckBox = new CheckBox(this);
        activeCheckBox.setChecked(active);
        row.addView(activeCheckBox);
        table.addView(row);
    }

    private String truncate(String s, int l) { return s.length() > l ? s.substring(0,l-3) + "..." : s; }

    private String[][] users =
            {
                    {"17awakil", "17awakil", "(none)"},
                    {"adambabs", "adambabs", "(none)"},
                    {"albat3ross", "Han", "Zhou"},
                    {"AlexanderAsfar", "AlexanderAsfar", "(none)"},
                    {"alitapan", "Ali", "Tapan"},
                    {"anasdeis", "Anas", "Deis"},
                    {"Anasshahidd21", "Anas", "Shahid"},
                    {"And7ew", "And7ew", "(none)"},
                    {"angel6497", "angel6497", "(none)"},
                    {"anthony-ubah", "anthony-ubah", "(none)"},
                    {"APorporino", "Anthony", "Porporino"},
                    {"archarbar", "Victor", "Zhong"},
                    {"arianit1999", "Arianit", "Vavla"},
                    {"arneetsinghkalra", "Arneet", "Kalra"},
                    {"benjaminweiss46", "Benjamin", "Weiss"},
                    {"BradMcBain", "Brad", "McBain"},
                    {"brendanmarks", "brendanmarks", "(none)"},
                    {"calebsh", "calebsh", "(none)"},
                    {"Catosine", "Cytosine", "(none)"},
                    {"cboustros", "cboustros", "(none)"},
                    {"chadspector", "chadspector", "(none)"},
                    {"Charles0115", "Charles0115", "(none)"},
                    {"CharlesBourbeau", "Charles", "Bourbeau"},
                    {"ChelseaM-C", "Chelsea", "Myers-Colet"},
                    {"Cpt0Teemo", "Yoan", "Poulmarc'k"},
                    {"danbensouss", "danbensouss", "(none)"},
                    {"DarienMC", "Darien", "Muse-Charbonneau"},
                    {"davidkronish", "David", "Kronish"},
                    {"DominicWen73", "Dominic", "Wener"},
                    {"du-hr", "Haoran", "Du"},
                    {"dumplingsforbreakfast", "Gloria", "Li"},
                    {"ecse321testuser", "ecse321testuser", "(none)"},
                    {"eliastam", "eliastam", "(none)"},
                    {"Elie-Elia", "Elie-Elia", "(none)"},
                    {"felixsimard", "Felix", "Simard"},
                    {"FunnyQDaniel", "FunnyQDaniel", "(none)"},
                    {"Georges1998", "George", "Kandalaft"},
                    {"ggnjt", "ggnjt", "(none)"},
                    {"glenxoseph", "glenxoseph", "(none)"},
                    {"gmourant", "Georges", "Mourant"},
                    {"gregpiche", "gregpiche", "(none)"},
                    {"hadizia", "hadizia", "(none)"},
                    {"haoweiqiu", "Haowei", "(none)"},
                    {"helen-m-lin", "helen-m-lin", "(none)"},
                    {"HuzaifaElahi", "Muhammad", "Huzaifa"},
                    {"idodin", "Imad", "Dodin"},
                    {"ImaneChafi", "ImaneChafi", "(none)"},
                    {"imbur", "Marton", "Bur"},
                    {"isaacdif", "Isaac", "Di"},
                    {"iyatan", "Iyatan", "Atchoro"},
                    {"jacobsilcoff", "Jacob", "Silcoff"},
                    {"jasondaou", "Jason", "Daou"},
                    {"jerryled", "jerryled", "(none)"},
                    {"JiMing-Li", "JiMing-Li", "(none)"},
                    {"jmrchow", "Jeremy", "Chow"},
                    {"KarlDoumar", "KarlDoumar", "(none)"},
                    {"karleid", "Karl", "Eid"},
                    {"kehwhy", "kehwhy", "(none)"},
                    {"konzert", "konzert", "(none)"},
                    {"kraglalbert", "Albert", "Kragl"},
                    {"Krownsi", "Alex", "Gruenwald"},
                    {"Kyjauna", "Kyjauna", "(none)"},
                    {"KyleDouglasMyers", "KyleDouglasMyers", "(none)"},
                    {"Langdo99", "Langdo99", "(none)"},
                    {"liutianci1887", "liutianci1887", "(none)"},
                    {"liuzheyu1998", "Zheyu", "Liu"},
                    {"loucadufault", "loucadufault", "(none)"},
                    {"luusteve", "luusteve", "(none)"},
                    {"M1984", "YuhangZhang", "(none)"},
                    {"mahroo12", "mahroo12", "(none)"},
                    {"MaireadMaloney", "Mairead", "Maloney"},
                    {"maizeng2008", "maizeng2008", "(none)"},
                    {"marw12", "marw12", "(none)"},
                    {"marwankanaan", "Marwan", "Kanaan"},
                    {"mattcaccavelli", "mattcaccavelli", "(none)"},
                    {"maxbuteau", "Maxime", "Buteau"},
                    {"mehdiammar", "Mehdi", "Ammar"},
                    {"menglinhe", "Menglin", "He"},
                    {"mertgurkan", "mertgurkan", "(none)"},
                    {"MichelMajdalani", "MichelMajdalani", "(none)"},
                    {"michr12", "Mich", "Riendeau"},
                    {"mlej8", "Michael", "Li"},
                    {"mlsmlk", "mlsmlk", "(none)"},
                    {"mnoor984", "mnoor984", "(none)"},
                    {"mrplt", "Murat", "Polat"},
                    {"mustafain117", "Mustafain", "Ali"},
                    {"NelsonZeng25", "Nelson", "Zeng"},
                    {"NicolasAbdelnour", "Nicolas", "Abdelnour"},
                    {"ocayci1", "Onur", "Cayci"},
                    {"OderoOtieno", "Odero", "Otieno"},
                    {"OldPineapple", "OldPineapple", "(none)"},
                    {"osmanvee", "Osman", "Warsi"},
                    {"PaulHooley", "Paul", "Hooley"},
                    {"petarbasta", "Petar", "Basta"},
                    {"pppparadox", "pppparadox", "(none)"},
                    {"preyansh98", "Preyansh", "Kaushik"},
                    {"randylimcgill", "randylimcgill", "(none)"},
                    {"rezaandwenhao", "Owen", "(Wenhao)"},
                    {"Rijul5", "RIJUL", "SAINI"},
                    {"ryanarndtsen", "ryanarndtsen", "(none)"},
                    {"samihilal1234", "samihilal1234", "(none)"},
                    {"sanihaseeb", "Sani", "Haseeb"},
                    {"schen136", "Grace", "Chen"},
                    {"sharonkat", "Sharon", "Kattar"},
                    {"Silectron", "Silectron", "(none)"},
                    {"sofiiad", "sofiiad", "(none)"},
                    {"ssmith21", "Sean", "Smith"},
                    {"sungengyi", "Gengyi", "Sun"},
                    {"talellia", "Alba", "Talelli"},
                    {"talhariaz3077", "Talha", "(none)"},
                    {"taylorlynn", "taylorlynn", "(none)"},
                    {"thehomelessredranger", "Edward", "Latulipe-Kang"},
                    {"tondro1", "Alex", "Choi"},
                    {"Tony9984", "Tony", "(none)"},
                    {"TrvTab", "TrvTab", "(none)"},
                    {"turekin", "turekin", "(none)"},
                    {"twedds95", "Patrick", "Tweddell"},
                    {"tylerrwatsonn", "tylerrwatsonn", "(none)"},
                    {"udchemen", "Uyiren", "Chemen"},
                    {"watem", "Matthew", "Williams"},
                    {"willbouch", "willbouch", "(none)"},
                    {"WilliamYkZhang", "William", "Zhang"},
                    {"Yuankang-Wei", "Frank-Wei", "(none)"},
                    {"zhengyucui", "Zheng", "Yu"},
                    {"ZijinNie", "Zijin", "Nie"}
            };
}
