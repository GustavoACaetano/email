package caetano.alves.email;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnEnviar = findViewById(R.id.btnEnviar);
        // Definição da ação do clique do botão
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtendo dados digitados pelo usuário

                // Dados de Email
                EditText etEmail = findViewById(R.id.etEmail); // Selecionando o campo de texto de email
                String email = etEmail.getText().toString(); // Salvando a string do campo de texto de email

                // Dados de assunto
                EditText etAssunto = findViewById(R.id.etAssunto); // Selecionando o campo de texto de assunto
                String assunto = etAssunto.getText().toString(); // Salvando a string do campo de texto de assunto

                // Dados de texto
                EditText etTexto = findViewById(R.id.etTexto); // Selecionando o campo de texto do corpo do email
                String texto = etTexto.getText().toString(); // Salvando a string do campo de texto do corpo do email

                // Intent para criar ação de redirecionar para enviar o email
                Intent i = new Intent(Intent.ACTION_SENDTO); // Intent de ação de enviar por algum programa
                i.setData(Uri.parse("mailto:")); // Indicar que é para os programas que executam a ação de enviar por email

                // Configurações para redirecionar pro email com os campos já preenchidos
                String[] emails = new String[]{email};
                i.putExtra(Intent.EXTRA_EMAIL, emails);
                i.putExtra(Intent.EXTRA_SUBJECT, assunto);
                i.putExtra(Intent.EXTRA_TEXT, texto);

                // Usando try para executar a intent, porque pode falhar se o dispositivo não tiver um app que executa a ação
                try {
                    startActivity(Intent.createChooser(i, "Escolha o APP"));
                    }
                catch (ActivityNotFoundException e){
                    Toast.makeText(MainActivity.this, "Não há nenhum app que posso realizar essa operação", Toast.LENGTH_LONG).show(); // Mensagem de erro
                }
            };
        });
    };
}