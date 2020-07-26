package com.example.roonrxjava;

import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import com.example.roonrxjava.entity.Person;

import org.w3c.dom.Text;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

import static com.example.roonrxjava.MyApplication.getPersonDatabase;

public class LocalPersonDataSource {

    private Context context;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public LocalPersonDataSource(Context context) {
        this.context = context;
    }

    public void createPerson(Person person) {
        Completable insert = getPersonDatabase().personDao().createPerson(person);
        compositeDisposable.add(insert
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> Toast.makeText(context, "添加成功", Toast.LENGTH_SHORT).show()));
    }

    public void getPersons(TextView textView) {
        Single<List<Person>> persons = getPersonDatabase().personDao().getPersons();
        compositeDisposable.add(persons
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(personList -> {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (Person person : personList) {
                        stringBuilder.append(person.toString()).append("\n");
                    }
                    textView.setText(stringBuilder);
                }));
    }
}
