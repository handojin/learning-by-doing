(ns dataomic-testdrive.datomic-init
  (require [datomic.api :as d :refer (q)]))

;define uri, schema, seed data
(def uri "datomic:mem://testdrive")
(def schema (read-string (slurp "resources/private/dtm/schema.dtm")))
(def seed (read-string (slurp "resources/private/dtm/seed.dtm")))

;initialization routes
(defn initialize-database []
  (when (d/create-database uri)
    (let [conn (d/connect uri)]
      (d/transact conn schema)
      (d/transact conn seed))))

