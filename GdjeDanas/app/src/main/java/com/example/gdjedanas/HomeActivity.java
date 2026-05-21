package com.example.gdjedanas;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gdjedanas.adapters.EventAdapter;
import com.example.gdjedanas.models.Event;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private EventAdapter adapter;

    private List<Event> eventList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.recyclerView);

        // prikaz eventa jedan ispod drugog

        recyclerView.setLayoutManager(
                new LinearLayoutManager(this));

        eventList = new ArrayList<>();


        // predstave

        eventList.add(
                new Event(
                        R.drawable.event2,
                        "Crnogorska svadba",
                        "Sarajevo",
                        "Dom mladih",
                        "08.10.2026",
                        "20:00",
                        "Predstava inspirisana tradicionalnim balkanskim običajima.",
                        4.8f,
                        152
                )
        );

        eventList.add(
                new Event(
                        R.drawable.event8,
                        "Crnogorska svadba",
                        "Mostar",
                        "HD Herceg Stjepana Kosača",
                        "07.10.2026",
                        "20:00",
                        "Popularna regionalna predstava sa humorističnim scenama.",
                        4.7f,
                        119
                )
        );


        eventList.add(
                new Event(
                        R.drawable.event1,
                        "Ko je lud",
                        "Zenica",
                        "Bosansko narodno pozorište",
                        "10.06.2026",
                        "20:00",
                        "Komedija koja na humorističan način prikazuje svakodnevnicu.",
                        4.9f,
                        210
                )
        );

        eventList.add(
                new Event(
                        R.drawable.event10,
                        "Da sam ja neko",
                        "Banja Luka",
                        "Banski Dvor",
                        "15.06.2026",
                        "20:00",
                        "Predstava inspirisana poznatim regionalnim pričama.",
                        4.5f,
                        90
                )
        );


        
        eventList.add(
                new Event(
                        R.drawable.event16,
                        "Nebitno Kerim Lutuna",
                        "Sarajevo",
                        "BKC Sarajevo",
                        "17.06.2026",
                        "20:00",
                        "Stand up nastup u okviru Garden of Dreams festivala.",
                        4.7f,
                        130
                )
        );

        eventList.add(
                new Event(
                        R.drawable.event17,
                        "Da sam ja neko",
                        "Međugorje",
                        "Herceg Etno selo",
                        "29.07.2026",
                        "21:00",
                        "Pozorišna predstava na otvorenom.",
                        4.6f,
                        101
                )
        );


        // sport

        eventList.add(
                new Event(
                        R.drawable.event3,
                        "BiH vs Sjeverna Makedonija",
                        "Sarajevo",
                        "Stadion Asim Ferhatović Hase",
                        "29.05.2026",
                        "20:30",
                        "Prijateljska utakmica reprezentacija Bosne i Hercegovine i Sjeverne Makedonije.",
                        4.8f,
                        480
                )
        );

        eventList.add(
                new Event(
                        R.drawable.event4,
                        "European Women's U-19 Championship 2026",
                        "Zenica",
                        "Bilino Polje",
                        "27.06.2026",
                        "15:00",
                        "Evropsko prvenstvo za žene do 19 godina.",
                        4.6f,
                        140
                )
        );

        eventList.add(
                new Event(
                        R.drawable.event5,
                        "Bosnia and Herzegovina VS Germany",
                        "Zenica",
                        "Trening centar Zenica",
                        "27.06.2026",
                        "17:00",
                        "Međunarodna utakmica ženskih reprezentacija.",
                        4.9f,
                        320
                )
        );

        eventList.add(
                new Event(
                        R.drawable.event6,
                        "Sweden VS Poland",
                        "Sarajevo",
                        "Stadion Grbavica",
                        "27.06.2026",
                        "20:00",
                        "Utakmica evropskog prvenstva U-19.",
                        4.7f,
                        205
                )
        );

        eventList.add(
                new Event(
                        R.drawable.event7,
                        "Bosnia and Herzegovina VS Sweden",
                        "Zenica",
                        "Bilino Polje",
                        "30.06.2026",
                        "17:00",
                        "Završna utakmica grupne faze prvenstva.",
                        4.8f,
                        298
                )
        );


        // muzika

        eventList.add(
                new Event(
                        R.drawable.event9,
                        "Who See",
                        "Sarajevo",
                        "Cinema Sloga",
                        "22.05.2026",
                        "20:00",
                        "Koncert popularnog hip hop dua iz Crne Gore.",
                        4.9f,
                        410
                )
        );

        eventList.add(
                new Event(
                        R.drawable.event11,
                        "Petar Grašo",
                        "Banja Luka",
                        "Tvrđava Kastel",
                        "02.07.2026",
                        "20:30",
                        "Veliki ljetni koncert Petra Graše na Kastelu.",
                        5.0f,
                        620
                )
        );

        eventList.add(
                new Event(
                        R.drawable.event12,
                        "Yasmin Levy",
                        "Banja Luka",
                        "Tvrđava Kastel",
                        "03.07.2026",
                        "20:30",
                        "Koncert svjetski poznate izvođačice Yasmin Levy.",
                        4.9f,
                        288
                )
        );

        eventList.add(
                new Event(
                        R.drawable.event13,
                        "Garden of Dreams Festival 2026",
                        "Sarajevo",
                        "Krov zgrade Svjetlosti",
                        "13-19.06.2026",
                        "18:00 - 01:00",
                        "Festival elektronske muzike i umjetnosti u Sarajevu.",
                        5.0f,
                        812
                )
        );

        eventList.add(
                new Event(
                        R.drawable.event14,
                        "Mostar Summer Fest 2026",
                        "Mostar",
                        "SC Kantarevac",
                        "02-04.07.2026",
                        "20:00",
                        "Jedan od najvećih muzičkih festivala u Hercegovini.",
                        4.8f,
                        510
                )
        );

        eventList.add(
                new Event(
                        R.drawable.event15,
                        "Fresh Wave Festival",
                        "Banja Luka",
                        "Tvrđava Kastel",
                        "06-08.08.2026",
                        "20:00",
                        "Festival elektronske muzike na tvrđavi Kastel.",
                        4.9f,
                        700
                )
        );


        // ostali događaji

        eventList.add(
                new Event(
                        R.drawable.event18,
                        "Sarajevo Film Festival",
                        "Sarajevo",
                        "Više lokacija",
                        "14-21.08.2026",
                        "Cjelodnevni program",
                        "Najpoznatiji filmski festival u regionu.",
                        5.0f,
                        1200
                )
        );

        eventList.add(
                new Event(
                        R.drawable.event19,
                        "Fina degustacija",
                        "Sarajevo",
                        "Hotel Bosna",
                        "05.06.2026",
                        "16:00 - 22:00",
                        "Degustacija vina i specijaliteta domaće kuhinje.",
                        4.5f,
                        76
                )
        );

        eventList.add(
                new Event(
                        R.drawable.event20,
                        "100 EXPO 2026",
                        "Sarajevo",
                        "Hotel Hills",
                        "20-21.10.2026",
                        "10:00",
                        "Međunarodni sajam privrede i inovacija.",
                        4.8f,
                        230
                )
        );


        // povezivanje adaptera

        adapter = new EventAdapter(eventList);

        recyclerView.setAdapter(adapter);
    }
}