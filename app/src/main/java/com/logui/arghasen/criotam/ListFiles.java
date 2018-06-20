package com.logui.arghasen.criotam;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListFiles extends AppCompatActivity {

    FilesListRecyclerAdapter filesRecyclerAdapter;
    ArrayList<String> files_list;

    @BindView(R.id.files_recycler)
    RecyclerView files_recycler;
    @BindView(R.id.refresh_files)
    SwipeRefreshLayout refresh_files;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_files_list);

        ButterKnife.bind(this);

        files_list = new ArrayList<>();

        refresh_files.setRefreshing(false);

        files_list.addAll(getIntent().getStringArrayListExtra("file_list"));

        filesRecyclerAdapter = new FilesListRecyclerAdapter(ListFiles.this, files_list, new FilesListRecyclerAdapter.ClickListener() {
            @Override
            public void onFileClickListener(View view, int position, String file_url) {
                Log.d("file path", file_url);
                downloadFile(file_url);
            }});

        files_recycler.setLayoutManager(new LinearLayoutManager(this));
        files_recycler.setAdapter(filesRecyclerAdapter);


        refresh_files.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh_files.setRefreshing(false);
            }
        });

    }

    private void downloadFile(String path) {

        final ProgressDialog dialog = new ProgressDialog(ListFiles.this);
        dialog.setMessage("downloading...");
        dialog.show();

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReferenceFromUrl("gs://criotam-bec9b.appspot.com");
        StorageReference  islandRef = storageRef.child(path);

        final File rootPath = new File(Environment.getExternalStorageDirectory(),
                "Criotam"+"/"+path);

        if(!rootPath.exists()) {
            rootPath.getParentFile().mkdirs();
        }

        //final File localFile = new File(rootPath,path);

        islandRef.getFile(rootPath).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                dialog.dismiss();
                Log.e("firebase ",";local tem file created  created " +rootPath.toString());
                //  updateDb(timestamp,localFile.toString(),position);
                //read and plot graph
                Intent intent = new Intent(ListFiles.this, ReadCsvFile.class);
                intent.putExtra("filename", rootPath.toString());
                startActivity(intent);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                dialog.dismiss();
                Log.e("firebase ",";local tem file not created  created " +exception.toString());
            }
        });
    }

}
