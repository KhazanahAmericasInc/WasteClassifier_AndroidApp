# # WasteClassifierMLKitApp

Hi! It is a **simple** waste classification android application developed with Google ML Kit and firebase storage. 

* I am using the model trained by AutoML api by H20.ai which is integrated in ML Kit. 

* If the image cannot be classified by the model, the application will ask users to provide the waste type if they know the answer. The image and the type will be uploaded to firebase storage automatically with user consent.

* It is a good quick practise to getting familiar with firebase, autoML, and ML Kit. (I spent two work days to develop the first version.)


## UI
* Welcome Page

![Welcome Page](https://github.com/clair-hu/WasteClassifierMLKitApp/blob/master/img/welcome_page.jpg)
* Main Page

![Main Page](https://github.com/clair-hu/WasteClassifierMLKitApp/blob/master/img/main_page.jpg)
* Gallery

![Gallery](https://github.com/clair-hu/WasteClassifierMLKitApp/blob/master/img/gallery.png)
* Camera

![Camera](https://github.com/clair-hu/WasteClassifierMLKitApp/blob/master/img/camera.jpg)
* Output Page (tested on my HUAWEI P20 device)

![Output Page](https://github.com/clair-hu/WasteClassifierMLKitApp/blob/master/img/prediction_compost_type1.jpg)
![Output Page](https://github.com/clair-hu/WasteClassifierMLKitApp/blob/master/img/prediction_compost_type2.jpg)
![Output Page](https://github.com/clair-hu/WasteClassifierMLKitApp/blob/master/img/prediction_recycle_type.jpg)

* Unable to predict image type page

![Unable to predict image type page](https://github.com/clair-hu/WasteClassifierMLKitApp/blob/master/img/unable_to_predict_page.jpg)
* Thanks for uploading image and type to firebase storage

![Thanks for uploading image and type to firebase storage](https://github.com/clair-hu/WasteClassifierMLKitApp/blob/master/img/thank_for_uploading.jpg)

## Firebase

### Connection with Android application
Firebase is a comprehensive mobile development platform. It is integrated with Google ML Kit.
![Connecting firebase to android application](https://github.com/clair-hu/WasteClassifierMLKitApp/blob/master/img/firebase_to_android_app.png)

### Storage
* Configure storage reference and metadata
* Organize uploading destination - folder organization
* Handling input changing
* Handling uploading task success and failure

## AutoML

AutoML vision is a new api integrated with Google ML Kit.

You need to prepare your dataset to classification. The limitation of the dataset size is 1000 images.

* Select autoML model latency and size

![Select autoML model latency and size](https://github.com/clair-hu/WasteClassifierMLKitApp/blob/master/img/autoML_model_latency_and_size_selection.png)
* Training model with AutoML

![Training model with AutoML](https://github.com/clair-hu/WasteClassifierMLKitApp/blob/master/img/autoML_training.png)
* Model evaluation

![Model evaluation](https://github.com/clair-hu/WasteClassifierMLKitApp/blob/master/img/model_evaluation.png)
* Confusion matrix

![Confusion matrix](https://github.com/clair-hu/WasteClassifierMLKitApp/blob/master/img/confusion_matrix.png)
* Publish model so we can use remotely in android app

![Publish model so we can use remotely in android app](https://github.com/clair-hu/WasteClassifierMLKitApp/blob/master/img/model_publish.png)

## Reference
* Front page image copyright: https://www.cnbc.com/2018/07/13/how-san-francisco-became-a-global-leader-in-waste-management.html

* Codes partially depend on https://github.com/jirawatee/ML-Kit-for-Firebase-Android.git by jirawatee.
