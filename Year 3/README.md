## Year 3 Projects

## Stock Market Analysis (Python, Scikit-learn, Data Science)
The Shenzhen and Shanghai stock exchanges are close in geography and therefore linked with one another, with arbitrage between the 2 exchanges being commonplace. This coursework was the analysis of the data of these markets and extracting trends that could be applied to make a profit.
These trends were found using unsupervised machine learning algorithms. An algorithm was first trained and then tested using scikit-learn. An analysis was then conducted on these trends.

## Multi-threading with OpenMP (High Performance Computing C++)
Simulating the particle diffusion using a step wise solution. Multi-threading using OpenMP to coordinate the threads was used due to the high number of calculations. The math also had to be done, with the correct limits and boundaries set. This was to introduce high performance computing on a dataset where such computing power was necesaary. This was done in order to share the strengths and weaknesses of high performance computing and see how to create a real life implementation.

## Song finding and storage (MicroService implementation Golang)
This coursework was similar to how shazam functions. A Base64 encoded .wav music file was submitted via a curl request. This was then used to find the full song using an alternate service's API. 
The songs could then be stored, retrieved and deleted in a local database. It was impemented in multiple different micro services in order to understand the strengths and weaknesses of such an implementation. Each function had a different microservice. In this case 1 function was interacting with the database. Another was the searching of the songs from the snippets. The final microservice was tying the tie different microservices together and handling any other interactions with the third party service.
(MS, music searcher also had SQL) CHECK WHERE THE DB WAS ACTUALLY STORED IN THE END

## Quickhull Implementation and Time & Space Complexity analysis (Python)
This coursework required an implementation of a famous algorithm. I chose quickhull because of its relation to the computer graphics module I was taking simultaneously.
There was also a time and space complexity analysis as well as a discussion on how different implementations would/wouldnt affect this.

## Jungle Scene (Computer Graphics)
The coursework was to make a semi-realistic jungle scene. The objects made up of meshes had to be textured, generated and shaded ourselves using self written shaders. The scene was 3d with being able to move around the scene using a camera. When the camera would move around, the shadow and lighting of the objects would adjust in accordance. Blinn-Phong shading was used because of its decreased computational power. I used OpenGL, a now deprecated program to load the object meshes. It also used a cubemap and an environment map to provide the shadow and texture. This was to give an introduction into how to generate a realistic scene and more importantly how to handle the camera moving around and the shading changing as a result.

