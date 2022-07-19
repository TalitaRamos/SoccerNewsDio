package br.com.elevii.soccernews;

import android.app.Application;

/**
 * FIXME Centralizar uma instância do contexto no app não é uma boa prática
 * O correto seria utilizar uma injeção de dependências (como o Dogger ou o Hilt)
 */
public class App extends Application {
    private static App instance;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
