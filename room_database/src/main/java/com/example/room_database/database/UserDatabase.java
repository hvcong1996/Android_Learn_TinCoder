package com.example.room_database.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.room_database.User;

// Tăng version của database nếu có thay đổi trong database
@Database(entities = User.class, version = 2)
public abstract class UserDatabase extends RoomDatabase {

    // Xử lý Migration khi thay đổi cấu trúc table trong database
    // Migration(old version, new version)
    private static Migration migration_from_1_to_2 = new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            // Thêm cột year vào table
            database.execSQL("ALTER TABLE user ADD COLUMN year TEXT");

            // Thêm n cột vào table
            // "ALTER TABLE table_name ADD COLUMN column_1,column_2,column_3 TEXT"

            // Chỉnh sửa kiểu dữ liệu trong cột
            // "ALTER TABLE table_name ALTER COLUMN column_name value_type"

            // Xóa cột trong bảng
            // "ALTER TABLE table_name DROP COLUMN column_name"
        }
    };

    private static final String DATA_BASE_NAME = "user.db";
    private static UserDatabase instanceDatabase;

    public static synchronized UserDatabase getInstance(Context context){
        if(instanceDatabase == null){
            instanceDatabase = Room.databaseBuilder(context.getApplicationContext(), UserDatabase.class, DATA_BASE_NAME)
                    .allowMainThreadQueries()
                    .addMigrations(migration_from_1_to_2)
                    .build();
            // Sử dụng .addMigrations(migration_from_1_to_2) để có thể thực hiện migrate
        }

        return instanceDatabase;
    }

    public abstract IUserDAO iUserDAO();
}
