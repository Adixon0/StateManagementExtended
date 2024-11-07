package pl.adrianizykowski.statemanagementextended;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class MainActivity extends AppCompatActivity {

    private TextView tekstLicznik;
    private Button przyciskZwieksz;
    private EditText poleTekstowe;
    private CheckBox poleZaznaczenie;
    private TextView tekstOpcji;
    private Switch przelacznikMotywu;

    private int licznik = 0;
    private String tekstUzytkownika = "";
    private boolean zaznaczenieOpcji = false;
    private boolean trybCiemny = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tekstLicznik = findViewById(R.id.tekstLicznik);
        przyciskZwieksz = findViewById(R.id.przyciskZwieksz);
        poleTekstowe = findViewById(R.id.poleTekstowe);
        poleZaznaczenie = findViewById(R.id.poleZaznaczenie);
        tekstOpcji = findViewById(R.id.tekstOpcji);
        przelacznikMotywu = findViewById(R.id.przelacznikMotywu);

        przyciskZwieksz.setOnClickListener(v -> {
            licznik++;
            tekstLicznik.setText("Licznik: " + licznik);
        });

        poleZaznaczenie.setOnCheckedChangeListener((buttonView, isChecked) -> {
            zaznaczenieOpcji = isChecked;
            tekstOpcji.setVisibility(isChecked ? View.VISIBLE : View.GONE);
        });

        przelacznikMotywu.setOnCheckedChangeListener((buttonView, isChecked) -> {
            trybCiemny = isChecked;
            ustawMotyw();
        });

        if (savedInstanceState != null) {
            licznik = savedInstanceState.getInt("stanLicznik");
            tekstUzytkownika = savedInstanceState.getString("stanTekst");
            zaznaczenieOpcji = savedInstanceState.getBoolean("stanZaznacz");
            trybCiemny = savedInstanceState.getBoolean("stanPrzelacznik");

            tekstLicznik.setText("Licznik: " + licznik);
            poleTekstowe.setText(tekstUzytkownika);
            poleZaznaczenie.setChecked(zaznaczenieOpcji);
            przelacznikMotywu.setChecked(trybCiemny);
            tekstOpcji.setVisibility(zaznaczenieOpcji ? View.VISIBLE : View.GONE);
            ustawMotyw();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("stanLicznik", licznik);
        outState.putString("stanTekst", poleTekstowe.getText().toString());
        outState.putBoolean("stanZaznacz", poleZaznaczenie.isChecked());
        outState.putBoolean("stanPrzelacznik", przelacznikMotywu.isChecked());
    }

    private void ustawMotyw() {
        if (trybCiemny) {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}
