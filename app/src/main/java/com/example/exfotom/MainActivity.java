package com.example.exfotom;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ImageView imagem;
    Button btLeitura;
    TextView txLeitura;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        retiraFoto();
        lerCodigos();
    }

    private void lerCodigos(){
        txLeitura=(TextView)findViewById(R.id.edtLeitura);
        btLeitura=(Button)findViewById(R.id.btnLerCodigo);
        btLeitura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                startActivityForResult(intent,0);
            }
        });
    }




    private void retiraFoto(){
        imagem=(ImageView)findViewById(R.id.suaImagem);
        Button botaoFoto;
        botaoFoto=(Button)findViewById(R.id.btnTiraFoto);
        botaoFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);
                //startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       /*Bundle bundle = data.getExtras();
        if (data !=null){
            Bitmap bitmap = (Bitmap)bundle.get("data");
            imagem.setImageBitmap(bitmap);
        }

        */
        if (requestCode==0){
            txLeitura.setText(data.getStringExtra("SCAN_RESULT"));
        }

    }
}