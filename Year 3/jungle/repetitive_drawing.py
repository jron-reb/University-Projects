from main import *

# A function to draw the trees with bamboo leaves. It is very basic and simple but not enough iterations to create a for loop, this was quicker and simpler despite being crude.
def draw_bamboo_leaves(self):
   bamboo_leaves = load_obj_file('models/BambooOBJ.obj')
   self.add_models_list(
       [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([0,-1,0]),scaleMatrix([.5,.5,.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='scene') for mesh in bamboo_leaves]
   )
   self.add_models_list(
       [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([5,-1,0]),scaleMatrix([.5,.5,.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='scene') for mesh in bamboo_leaves]
   )
   self.add_models_list(
       [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([-2,-1,0]),scaleMatrix([.5,.5,.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='scene') for mesh in bamboo_leaves]
   )
   self.add_models_list(
       [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([-6,-1,0]),scaleMatrix([.5,.5,.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='scene') for mesh in bamboo_leaves]
   )
   self.add_models_list(
       [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([4.5,-1,-4]),scaleMatrix([.5,.5,.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='scene') for mesh in bamboo_leaves]
   )

   self.add_models_list(
       [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([0,-1,5]),scaleMatrix([.5,.5,.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='scene') for mesh in bamboo_leaves]
   )
   self.add_models_list(
       [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([1,-1,5]),scaleMatrix([.5,.5,.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='scene') for mesh in bamboo_leaves]
   )
   # self.add_models_list(
   #     [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([3,-1,5]),scaleMatrix([.5,.5,.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='scene') for mesh in bamboo_leaves]
   # )
   # self.add_models_list(
   #     [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([5,-1,5]),scaleMatrix([.5,.5,.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='scene') for mesh in bamboo_leaves]
   # )
   # self.add_models_list(
   #     [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([7,-1,5]),scaleMatrix([.5,.5,.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='scene') for mesh in bamboo_leaves]
   # )
   # self.add_models_list(
   #     [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([9,-1,5]),scaleMatrix([.5,.5,.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='scene') for mesh in bamboo_leaves]
   # )
   # self.add_models_list(
   #     [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([11,-1,5]),scaleMatrix([.5,.5,.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='scene') for mesh in bamboo_leaves]
   # )

   self.add_models_list(
       [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([-1,-1,5]),scaleMatrix([.5,.5,.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='scene') for mesh in bamboo_leaves]
   )
   # self.add_models_list(
   #     [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([-3,-1,5]),scaleMatrix([.5,.5,.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='scene') for mesh in bamboo_leaves]
   # )
   # self.add_models_list(
   #     [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([-5,-1,5]),scaleMatrix([.5,.5,.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='scene') for mesh in bamboo_leaves]
   # )
   # self.add_models_list(
   #     [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([-7,-1,5]),scaleMatrix([.5,.5,.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='scene') for mesh in bamboo_leaves]
   # )
   # self.add_models_list(
   #     [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([-9,-1,5]),scaleMatrix([.5,.5,.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='scene') for mesh in bamboo_leaves]
   # )
   # self.add_models_list(
   #     [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([-11,-1,5]),scaleMatrix([.5,.5,.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='scene') for mesh in bamboo_leaves]
   # )

# A function to draw the sections of the floor
def draw_floor(self):
    floor = load_obj_file('models/floor.obj')
    self.add_models_list(
        [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([0,-1,0]),scaleMatrix([0.5,0.5,0.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='scene') for mesh in floor]
    )
    self.add_models_list(
       [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([0,-1,-5]),scaleMatrix([0.5,0.5,0.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='sceneagain') for mesh in floor]
    )
    self.add_models_list(
       [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([0,-1,-10]),scaleMatrix([0.5,0.5,0.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='sceneagain') for mesh in floor]
    )
    self.add_models_list(
       [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([0,-1,-15]),scaleMatrix([0.5,0.5,0.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='sceneagain') for mesh in floor]
    )
    self.add_models_list(
       [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([0,-1,5]),scaleMatrix([0.5,0.5,0.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='sceneagain') for mesh in floor]
    )
    self.add_models_list(
       [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([0,-1,10]),scaleMatrix([0.5,0.5,0.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='sceneagain') for mesh in floor]
    )
    self.add_models_list(
       [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([0,-1,15]),scaleMatrix([0.5,0.5,0.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='sceneagain') for mesh in floor]
    )
    self.add_models_list(
       [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([0,-1,20]),scaleMatrix([0.5,0.5,0.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='sceneagain') for mesh in floor]
    )
    self.add_models_list(
       [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([-5,-1,-5]),scaleMatrix([0.5,0.5,0.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='sceneagain') for mesh in floor]
    )
    self.add_models_list(
       [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([-5,-1,-10]),scaleMatrix([0.5,0.5,0.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='sceneagain') for mesh in floor]
    )
    self.add_models_list(
       [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([-5,-1,-15]),scaleMatrix([0.5,0.5,0.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='sceneagain') for mesh in floor]
    )
    self.add_models_list(
       [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([-5,-1,5]),scaleMatrix([0.5,0.5,0.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='sceneagain') for mesh in floor]
    )
    self.add_models_list(
       [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([-5,-1,10]),scaleMatrix([0.5,0.5,0.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='sceneagain') for mesh in floor]
    )
    self.add_models_list(
       [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([-5,-1,15]),scaleMatrix([0.5,0.5,0.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='sceneagain') for mesh in floor]
    )
    self.add_models_list(
       [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([-5,-1,20]),scaleMatrix([0.5,0.5,0.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='sceneagain') for mesh in floor]
    )
    self.add_models_list(
       [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([-5,-1,0]),scaleMatrix([0.5,0.5,0.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='sceneagain') for mesh in floor]
    )
    self.add_models_list(
       [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([-10,-1,-5]),scaleMatrix([0.5,0.5,0.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='sceneagain') for mesh in floor]
    )
    self.add_models_list(
       [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([-10,-1,-10]),scaleMatrix([0.5,0.5,0.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='sceneagain') for mesh in floor]
    )
    self.add_models_list(
       [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([-10,-1,-15]),scaleMatrix([0.5,0.5,0.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='sceneagain') for mesh in floor]
    )
    self.add_models_list(
       [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([-10,-1,5]),scaleMatrix([0.5,0.5,0.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='sceneagain') for mesh in floor]
    )
    self.add_models_list(
       [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([-10,-1,10]),scaleMatrix([0.5,0.5,0.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='sceneagain') for mesh in floor]
    )
    self.add_models_list(
       [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([-10,-1,15]),scaleMatrix([0.5,0.5,0.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='sceneagain') for mesh in floor]
    )
    self.add_models_list(
       [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([-10,-1,20]),scaleMatrix([0.5,0.5,0.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='sceneagain') for mesh in floor]
    )
    self.add_models_list(
       [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([-10,-1,0]),scaleMatrix([0.5,0.5,0.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='sceneagain') for mesh in floor]
    )
    self.add_models_list(
       [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([-15,-1,0]),scaleMatrix([0.5,0.5,0.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='sceneagain') for mesh in floor]
    )
    self.add_models_list(
        [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([-15,-1,0]),scaleMatrix([0.5,0.5,0.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='scene') for mesh in floor]
    )
    self.add_models_list(
       [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([-15,-1,-5]),scaleMatrix([0.5,0.5,0.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='sceneagain') for mesh in floor]
    )
    self.add_models_list(
       [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([-15,-1,-10]),scaleMatrix([0.5,0.5,0.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='sceneagain') for mesh in floor]
    )
    self.add_models_list(
       [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([-15,-1,-15]),scaleMatrix([0.5,0.5,0.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='sceneagain') for mesh in floor]
    )
    self.add_models_list(
       [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([-15,-1,5]),scaleMatrix([0.5,0.5,0.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='sceneagain') for mesh in floor]
    )
    self.add_models_list(
       [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([-15,-1,10]),scaleMatrix([0.5,0.5,0.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='sceneagain') for mesh in floor]
    )
    self.add_models_list(
       [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([-15,-1,15]),scaleMatrix([0.5,0.5,0.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='sceneagain') for mesh in floor]
    )
    self.add_models_list(
       [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([-15,-1,20]),scaleMatrix([0.5,0.5,0.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='sceneagain') for mesh in floor]
    )
    self.add_models_list(
        [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([5,-1,0]),scaleMatrix([0.5,0.5,0.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='scene') for mesh in floor]
    )
    self.add_models_list(
       [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([5,-1,-5]),scaleMatrix([0.5,0.5,0.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='sceneagain') for mesh in floor]
    )
    self.add_models_list(
       [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([5,-1,-10]),scaleMatrix([0.5,0.5,0.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='sceneagain') for mesh in floor]
    )
    self.add_models_list(
       [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([5,-1,-15]),scaleMatrix([0.5,0.5,0.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='sceneagain') for mesh in floor]
    )
    self.add_models_list(
       [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([5,-1,5]),scaleMatrix([0.5,0.5,0.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='sceneagain') for mesh in floor]
    )
    self.add_models_list(
       [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([5,-1,10]),scaleMatrix([0.5,0.5,0.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='sceneagain') for mesh in floor]
    )
    self.add_models_list(
       [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([5,-1,15]),scaleMatrix([0.5,0.5,0.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='sceneagain') for mesh in floor]
    )
    self.add_models_list(
       [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([5,-1,20]),scaleMatrix([0.5,0.5,0.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='sceneagain') for mesh in floor]
    )
    self.add_models_list(
       [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([5,-1,0]),scaleMatrix([0.5,0.5,0.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='sceneagain') for mesh in floor]
    )
    self.add_models_list(
           [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([0,-1,0]),scaleMatrix([0.5,0.5,0.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='scene') for mesh in floor]
    )
    self.add_models_list(
       [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([10,-1,-5]),scaleMatrix([0.5,0.5,0.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='sceneagain') for mesh in floor]
    )
    self.add_models_list(
       [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([10,-1,-10]),scaleMatrix([0.5,0.5,0.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='sceneagain') for mesh in floor]
    )
    self.add_models_list(
       [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([10,-1,-15]),scaleMatrix([0.5,0.5,0.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='sceneagain') for mesh in floor]
    )
    self.add_models_list(
       [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([10,-1,5]),scaleMatrix([0.5,0.5,0.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='sceneagain') for mesh in floor]
    )
    self.add_models_list(
       [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([10,-1,10]),scaleMatrix([0.5,0.5,0.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='sceneagain') for mesh in floor]
    )
    self.add_models_list(
       [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([10,-1,15]),scaleMatrix([0.5,0.5,0.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='sceneagain') for mesh in floor]
    )
    self.add_models_list(
       [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([10,-1,20]),scaleMatrix([0.5,0.5,0.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='sceneagain') for mesh in floor]
    )
    self.add_models_list(
       [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([10,-1,0]),scaleMatrix([0.5,0.5,0.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='sceneagain') for mesh in floor]
    )
    self.add_models_list(
        [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([15,-1,0]),scaleMatrix([0.5,0.5,0.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='scene') for mesh in floor]
    )
    self.add_models_list(
       [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([15,-1,-5]),scaleMatrix([0.5,0.5,0.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='sceneagain') for mesh in floor]
    )
    self.add_models_list(
       [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([15,-1,-10]),scaleMatrix([0.5,0.5,0.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='sceneagain') for mesh in floor]
    )
    self.add_models_list(
       [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([15,-1,-15]),scaleMatrix([0.5,0.5,0.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='sceneagain') for mesh in floor]
    )
    self.add_models_list(
       [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([15,-1,5]),scaleMatrix([0.5,0.5,0.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='sceneagain') for mesh in floor]
    )
    self.add_models_list(
       [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([15,-1,10]),scaleMatrix([0.5,0.5,0.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='sceneagain') for mesh in floor]
    )
    self.add_models_list(
       [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([15,-1,15]),scaleMatrix([0.5,0.5,0.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='sceneagain') for mesh in floor]
    )
    self.add_models_list(
       [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([15,-1,20]),scaleMatrix([0.5,0.5,0.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='sceneagain') for mesh in floor]
    )
    self.add_models_list(
       [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([15,-1,0]),scaleMatrix([0.5,0.5,0.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='sceneagain') for mesh in floor]
    )

