(ns example.app
  (:require
   ["expo" :as ex]
   ["react-native" :as rn]
   ["react" :as react]
   [re-frisk-remote.core :refer [enable-re-frisk-remote!]]
   [reagent.core :as r]
   [re-frame.core :as rf]
   [shadow.expo :as expo]
   [example.events]
   [example.subs]
   [example.component :as c]))



;; must use defonce and must refresh full app so metro can fill these in
;; at live-reload time `require` does not exist and will cause errors
;; must use path relative to :output-dir

;; turns on re-frame-10x
(enable-re-frisk-remote! {:enable-re-frame-10x? true})

(defonce splash-img (js/require "../assets/shadow-cljs.png"))

(def styles
  ^js (-> {:container
           {:flex 1
            :backgroundColor "#fff"
            :alignItems "center"
            :justifyContent "center"}
           :title
           {:fontWeight "bold"
            :fontSize 24
            :color "blue"}
           :button
           {:fontWeight "bold"
            :fontSize 18
            :padding 6
            :backgroundColor "blue"
            :borderRadius 10}
           :buttonText
           {:paddingLeft 12
            :paddingRight 12
            :fontWeight "bold"
            :fontSize 18
            :color "white"}
           :label
           {:fontWeight "normal"
            :fontSize 15
            :color "blue"}}
          (clj->js)
          (rn/StyleSheet.create)))

(defn root []
  (let [counter (rf/subscribe [:get-counter])]
    (fn []

      [c/view {:style (.-container styles)}
        [c/input {:placeholder "React Native Element Input"}]
        [c/text  {:style (.-title styles)} "Clicked: " @counter]
        [c/touchableopacity {:style (.-button styles)
                                 :on-press #(rf/dispatch [:inc-counter])}
              [c/text {:style (.-buttonText styles)} "Click me, I'll count"]

        ]

        [c/image {:source splash-img :style {:width 200 :height 200}}]
        [c/text {:style (.-label styles)} "Using: shadow-cljs+expo+reagent+re-frame"]
      ]

)))

(defn start
  {:dev/after-load true}
  []
  (expo/render-root (r/as-element [root])))

(defn init []
  (rf/dispatch-sync [:initialize-db])
  (start))
