(ns example.component
(:require   [reagent.core :as r :refer [atom]]
            ["expo" :as ex]
            ["react-native-elements" :as rne]
            ["react-native" :as rn]
            ["react" :as react])
  )

;; create reagent component from react native components

;; all you need is to adapt the react class into a reagent class that can then
;; be displayed in the app

 (def view (r/adapt-react-class rn/View))
 (def image (r/adapt-react-class rn/Image))
 (def text (r/adapt-react-class rn/Text))
 (def touchableopacity (r/adapt-react-class rn/TouchableOpacity))


 ;;react native elements
 (def input (r/adapt-react-class rne/Input))
