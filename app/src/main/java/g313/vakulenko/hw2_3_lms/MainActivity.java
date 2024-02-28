package g313.vakulenko.hw2_3_lms;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Stream;

public class MainActivity extends AppCompatActivity {

    private int id = 0;
    private EditText inputName;
    private EditText inputAuthor;
    private TextView output;
    private Button buttonAdd;
    private Button buttonSort;
    private Button buttonClear;
    private Book[] books;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Привязываем элементы интерфейса к полям класса
        inputName = findViewById(R.id.inputName);
        inputAuthor = findViewById(R.id.inputAuthor);
        output = findViewById(R.id.output);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonSort = findViewById(R.id.buttonSort);
        buttonClear = findViewById(R.id.buttonClear);

        // Создаем массив для хранения книг
        books = new Book[100];

        // Устанавливаем слушатели на кнопки
        buttonAdd.setOnClickListener(addListener);
        buttonSort.setOnClickListener(sortListener);
        buttonClear.setOnClickListener(clearListener);
    }

    // Обработчик нажатия кнопки "Добавить в картотеку"
    private View.OnClickListener addListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // Получаем введенные пользователем данные
            String name = inputName.getText().toString();
            String author = inputAuthor.getText().toString();

            // Проверяем, что оба поля заполнены
            if (!name.isEmpty() && !author.isEmpty()) {
                id++;
                Book book = new Book(id, name, author);
                books[id - 1] = book;
                updateBookList();
                inputName.setText("");
                inputAuthor.setText("");
            } else {
                // Если хотя бы одно поле пустое, выводим оповещение
                Toast.makeText(MainActivity.this, "Пожалуйста, введите название книги и автора", Toast.LENGTH_SHORT).show();
            }
        }
    };

    // Обработчик нажатия кнопки "Сортировать по авторам"
    private View.OnClickListener sortListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            sortBooksByAuthor();
            updateBookIds();
            updateBookList();
        }
    };

    // Обработчик нажатия кнопки "Очистить список"
    private View.OnClickListener clearListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            clearBookList();
        }
    };

    // Метод сортировки книг по авторам
    private void sortBooksByAuthor() {
        Arrays.sort(books, new Comparator<Book>() {
            @Override
            public int compare(Book book1, Book book2) {
                if (book1 == null || book2 == null) {
                    return 0;
                }
                return book1.getAuthor().compareTo(book2.getAuthor());
            }
        });
    }

    // Метод обновления номеров книг в списке
    private void updateBookIds() {
        for (int i = 0; i < books.length; i++) {
            if (books[i] != null) {
                books[i].setId(i + 1);
            }
        }
    }

    // Метод обновления списка книг на экране
    private void updateBookList() {
        output.setText("");
        Stream<Book> bookStream = Arrays.stream(books);
        bookStream.filter(Objects::nonNull)
                .forEach(s -> output.append(s.getId() + ". Книга - \"" + s.getName() + "\", Автор - " + s.getAuthor() + "\n"));
    }

    // Метод очистки списка книг
    private void clearBookList() {
        Arrays.fill(books, null);
        output.setText("");
    }
}
