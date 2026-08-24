from pymongo import MongoClient
from pymongo.errors import PyMongoError


class AnimalShelter:
    """CRUD operations for the Animal collection in MongoDB."""

    def __init__(self, username, password):
        HOST = "localhost"
        PORT = 27017
        DB = "aac"
        COL = "animals"

        connection_string = (
            f"mongodb://{username}:{password}@{HOST}:{PORT}/"
            f"?authSource=admin"
        )

        self.client = MongoClient(connection_string)
        self.database = self.client[DB]
        self.collection = self.database[COL]

    def create(self, data):
        """Insert one document into the animals collection."""
        if not isinstance(data, dict) or not data:
            return False

        try:
            result = self.collection.insert_one(data)
            return result.inserted_id is not None
        except PyMongoError as error:
            print(f"Create failed: {error}")
            return False

    def read(self, query):
        """Return documents matching the supplied query."""
        if not isinstance(query, dict):
            return []

        try:
            return list(self.collection.find(query))
        except PyMongoError as error:
            print(f"Read failed: {error}")
            return []

    def update(self, query, new_values):
        """Update documents matching the supplied query."""
        if not isinstance(query, dict) or not isinstance(new_values, dict):
            return 0

        try:
            result = self.collection.update_many(
                query,
                {"$set": new_values}
            )
            return result.modified_count
        except PyMongoError as error:
            print(f"Update failed: {error}")
            return 0

    def delete(self, query):
        """Delete documents matching the supplied query."""
        if not isinstance(query, dict):
            return 0

        try:
            result = self.collection.delete_many(query)
            return result.deleted_count
        except PyMongoError as error:
            print(f"Delete failed: {error}")
            return 0