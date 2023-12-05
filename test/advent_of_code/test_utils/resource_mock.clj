(ns advent-of-code.test-utils.resource-mock
  (:require [advent-of-code.utils :as utils]))

(def ACCESS_UNMOCKED_RESOURCE_MESSAGE
  "Attempted to access resource which has not been mocked")

;; `with-redefs` is a very useful test utility, but it rebinds a symbol globally across all threads
;; As such, use of `with-redefs` in tests requires tests be run serially to avoid tests interfering with each other
;; Given tests will be run serially, this mock uses global state to enable convenient usage in tests

(defonce mocked-resources-atom (atom {}))

(defmacro mock-resources [mock-resource-map & body]
  (when-not (map? mock-resource-map)
    (throw (IllegalArgumentException. "mock-resources expects a map of resource paths to mock resource values")))
  (let [prior-mocks-sym (gensym "prior-mocks__")]
    `(let [~prior-mocks-sym @mocked-resources-atom]
       (swap! mocked-resources-atom merge ~mock-resource-map)
       (try (do ~@body)
            (finally (reset! mocked-resources-atom ~prior-mocks-sym))))))

(defn get-mocked-resource! [path]
  (if (contains? @mocked-resources-atom path)
    (get @mocked-resources-atom path)
    (throw (ex-info ACCESS_UNMOCKED_RESOURCE_MESSAGE
                    {:path path
                     :mocked-resources @mocked-resources-atom}))))

(defn mock-resources-fixture [f]
  (with-redefs [utils/get-resource! get-mocked-resource!]
    (f)))
