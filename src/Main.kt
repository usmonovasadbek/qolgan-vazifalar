class Book(val title: String, val author: String, val genre: Genre) {
    var isAvailable: Boolean = true

    fun borrowBook(): Boolean {
        return if (isAvailable) {
            isAvailable = false
            println("Kitob olingan: $title")
            true
        } else {
            println("Kitob mavjud emas: $title")
            false
        }
    }

    fun returnBook() {
        isAvailable = true
        println("Kitob qaytarildi: $title")
    }
}
enum class Genre {
    FICTION, DETECTIVE, HISTORY, SCIENCE, FANTASY
}
class Reader(val name: String) {
    private val borrowedBooks = mutableListOf<Book>()

    fun borrow(book: Book) {
        if (book.borrowBook()) {
            borrowedBooks.add(book)
        }
    }

    fun returnBook(book: Book) {
        if (borrowedBooks.contains(book)) {
            book.returnBook()
            borrowedBooks.remove(book)
        } else {
            println("Bu kitob sizda emas: ${book.title}")
        }
    }

    fun listBorrowedBooks() {
        if (borrowedBooks.isEmpty()) {
            println("$name hech qanday kitob olmagan.")
        } else {
            println("$name olgan kitoblar:")
            borrowedBooks.forEach { book -> println(" - ${book.title}") }
        }
    }
}
class Reader1(val name: String) {
    private val borrowedBooks = mutableListOf<Book>()

    fun borrow(book: Book) {
        if (book.borrowBook()) {
            borrowedBooks.add(book)
        }
    }

    fun returnBook(book: Book) {
        if (borrowedBooks.contains(book)) {
            book.returnBook()
            borrowedBooks.remove(book)
        } else {
            println("Bu kitob sizda emas: ${book.title}")
        }
    }

    fun listBorrowedBooks() {
        if (borrowedBooks.isEmpty()) {
            println("$name hech qanday kitob olmagan.")
        } else {
            println("$name olgan kitoblar:")
            borrowedBooks.forEach { book -> println(" - ${book.title}") }
        }
    }
}
class Library(val name: String) {
    private val books = mutableListOf<Book>()
    private val readers = mutableListOf<Reader>()

    fun addBook(book: Book) {
        books.add(book)
        println("Kitob qo‘shildi: ${book.title}")
    }

    fun addReader(reader: Reader) {
        readers.add(reader)
        println("O‘quvchi qo‘shildi: ${reader.name}")
    }

    fun listAvailableBooks() {
        val availableBooks = books.filter { it.isAvailable }
        if (availableBooks.isEmpty()) {
            println("Kutubxonada hozircha mavjud kitob yo'q.")
        } else {
            println("Mavjud kitoblar:")
            availableBooks.forEach { book -> println(" - ${book.title} by ${book.author} (${book.genre})") }
        }
    }

    fun findBookByTitle(title: String): Book? {
        return books.find { it.title.equals(title, ignoreCase = true) }
    }

    fun listBooksByGenre(genre: Genre) {
        val booksByGenre = books.filter { it.genre == genre }
        if (booksByGenre.isEmpty()) {
            println("Ushbu janrda kitob mavjud emas: $genre")
        } else {
            println("$genre janridagi kitoblar:")
            booksByGenre.forEach { book -> println(" - ${book.title} by ${book.author}") }
        }
    }
}
fun main() {
    val library = Library("Markaziy kutubxona")

    val book1 = Book("1984", "George Orwell", Genre.FICTION)
    val book2 = Book("Sherlock Holmes", "Arthur Conan Doyle", Genre.DETECTIVE)
    val book3 = Book("A Brief History of Time", "Stephen Hawking", Genre.SCIENCE)

    library.addBook(book1)
    library.addBook(book2)
    library.addBook(book3)

    val reader1 = Reader("Ali")
    library.addReader(reader1)

    library.listAvailableBooks()

    reader1.borrow(book1)
    reader1.listBorrowedBooks()

    library.listAvailableBooks()

    reader1.returnBook(book1)
    reader1.listBorrowedBooks()

    library.listBooksByGenre(Genre.DETECTIVE)
}
