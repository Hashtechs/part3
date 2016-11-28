package hashtech.com.booklisting.data;

import android.provider.BaseColumns;

/**
 * Created by MFQ on 11/28/16.
 */

public class BookContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private BookContract() {}

    /**
     * Inner class that defines constant values for the pets database table.
     * Each entry in the table represents a single pet.
     */
    public static final class BookEntry implements BaseColumns {

        /** Name of database table for book */
        public final static String TABLE_NAME = "book";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_BOOK_NAME ="name";
    }
}
