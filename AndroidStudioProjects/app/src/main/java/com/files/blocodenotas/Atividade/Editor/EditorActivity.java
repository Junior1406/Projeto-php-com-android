package com.files.blocodenotas.Atividade.Editor;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.files.blocodenotas.Api.ApiInterface;
import com.files.blocodenotas.R;
import com.thebluealliance.spectrum.SpectrumPalette;

public class EditorActivity extends AppCompatActivity implements EditorView {

    EditText et_titulo, et_nota;
    ProgressDialog progressDialog;
    SpectrumPalette palette;

    EditorPresenter presenter;

    int cor, id;
    String titulo, nota;

    Menu actionMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        et_titulo = findViewById(R.id.titulo);
        et_nota = findViewById(R.id.nota);
        palette = findViewById(R.id.palette);

        palette.setOnColorSelectedListener(
                clr -> cor = clr
        );
        //cor padrao
        palette.setSelectedColor(getResources().getColor(R.color.branco));
        cor = getResources().getColor(R.color.branco);
        //carregamento
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Aguarde...");

        presenter = new EditorPresenter(this);

        Intent intent = getIntent();
        id = intent.getIntExtra("id",0);
        titulo = intent.getStringExtra("titulo");
        nota = intent.getStringExtra("nota");
        cor = intent.getIntExtra("cor",0);


        setDataFromItentExtra();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_editor, menu);
        actionMenu = menu;

        if (id != 0){
            actionMenu.findItem(R.id.editar).setVisible(true);
            actionMenu.findItem(R.id.deletar).setVisible(true);
            actionMenu.findItem(R.id.salvar).setVisible(false);
            actionMenu.findItem(R.id.atualizar).setVisible(false);
        }else{
            actionMenu.findItem(R.id.editar).setVisible(false);
            actionMenu.findItem(R.id.deletar).setVisible(false);
            actionMenu.findItem(R.id.salvar).setVisible(true);
            actionMenu.findItem(R.id.atualizar).setVisible(false);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String titulo = et_titulo.getText().toString().trim();
        String nota = et_nota.getText().toString().trim();
        int cor = this.cor;

        switch (item.getItemId()){
            case R.id.salvar:

                if(titulo.isEmpty()){
                    et_titulo.setError("Por favor Digite um Titulo ");
                }else if(nota.isEmpty()){
                    et_nota.setError("Por favor Escreva uma Nota ");
                }else {
                   presenter.saveNota(titulo, nota, cor);
                }
                return true;

            case R.id.editar:

                editMode();
                actionMenu.findItem(R.id.editar).setVisible(false);
                actionMenu.findItem(R.id.deletar).setVisible(false);
                actionMenu.findItem(R.id.salvar).setVisible(false);
                actionMenu.findItem(R.id.atualizar).setVisible(true);

                return true;

            case R.id.atualizar:
                if(titulo.isEmpty()){
                    et_titulo.setError("Por favor Digite um Titulo ");
                }else if(nota.isEmpty()){
                    et_nota.setError("Por favor Escreva uma Nota ");
                }else {
                    presenter.updateNota(id, titulo, nota, cor);
                }

                return true;

            case  R.id.deletar:
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                alertDialog.setTitle("Comfirmar!");
                alertDialog.setMessage("Você tem Certeza?");
                alertDialog.setNegativeButton("Sim", (dialog, which) -> {
                    dialog.dismiss();
                        presenter.deleteNota(id);
                });
                alertDialog.setPositiveButton("Cancelar",
                        (dialog, which) -> dialog.dismiss());

                alertDialog.show();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void showProgress() {
        progressDialog.show();

    }

    @Override
    public void hideProgress() {
        progressDialog.hide();

    }

    @Override
    public void onRequestSuccess(String menssagem) {
        Toast.makeText(EditorActivity.this,
                menssagem,
                Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK);
        finish();

    }

    @Override
    public void onRequestError(String menssagem) {
        Toast.makeText(EditorActivity.this,
                menssagem,
                Toast.LENGTH_SHORT).show();

    }
    private void setDataFromItentExtra() {

        if (id != 0){
            et_titulo.setText(titulo);
            et_nota.setText(nota);
            palette.setSelectedColor(cor);

            getSupportActionBar().setTitle("Atualizar Nota");
            readMode();
        }else {
            palette.setSelectedColor(getResources().getColor(R.color.branco));
            cor = getResources().getColor(R.color.branco);
            editMode();
        }

    }

    private void editMode() {
        et_titulo.setFocusableInTouchMode(true);
        et_nota.setFocusableInTouchMode(true);
        palette.setEnabled(true);
    }

    private void readMode() {
        et_titulo.setFocusableInTouchMode(false);
        et_nota.setFocusableInTouchMode(false);
        et_titulo.setFocusable(false);
        et_nota.setFocusable(false);
        palette.setEnabled(false);
    }


}