import pygame

# import the scene class
from cubeMap import FlattenCubeMap
from scene import Scene

from lightSource import LightSource

from blender import load_obj_file

from BaseModel import DrawModelFromMesh

from shaders import *

from ShadowMapping import *

from sphereModel import Sphere

from skyBox import *

from environmentMapping import *

from repetitive_drawing import *

class ExeterScene(Scene):
    def __init__(self):
        Scene.__init__(self)

        # Render the light source, it is coming from behind the viewpoint, so that the light shines on the jungle scene
        self.light = LightSource(self, position=[0, 4, 10])

        # Use the phone shader
        self.shaders='phong'

        # for shadow map rendering
        self.shadows = ShadowMap(light=self.light)
        self.show_shadow_map = ShowTexture(self, self.shadows)

        '''
        This loads the first type of bamboo tree (has no leaves and leans slightly to the right). 
        Everytime a model is added to the list a new bamboo tree is added to the scene.
        When a model is loaded it is translated and rotated so it goes in the desired location.
        The shading, shadows and texture mapping is dealt with when adding the model to the scene as well. This is the same with each model added
        '''
        bamboo = load_obj_file('models/bamboo.obj')
        self.add_models_list(
            [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([2,-2,2]),scaleMatrix([.5,0.1,.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='scene') for mesh in bamboo]
        )
        self.add_models_list(
            [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([1.7,-2,1.8]),scaleMatrix([.2,0.05,.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='scene') for mesh in bamboo]
        )
        self.add_models_list(
            [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([1,-2,2]),scaleMatrix([.5,0.1,.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='scene') for mesh in bamboo]
        )
        self.add_models_list(
            [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([2.2,-2,2.-3]),scaleMatrix([.5,0.1,.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='scene') for mesh in bamboo]
        )
        self.add_models_list(
            [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([-5,-2,2.-3]),scaleMatrix([.5,0.1,.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='scene') for mesh in bamboo]
        )
        self.add_models_list(
            [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([-6,-2,2.-3]),scaleMatrix([.5,0.1,.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='scene') for mesh in bamboo]
        )
        self.add_models_list(
            [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([-7,-2,2.-3]),scaleMatrix([.5,0.1,.5])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='scene') for mesh in bamboo]
        )

        # The second and most simplistic type of bamboo is drawn here (it goes straight up in the air and has no leaves)
        quick_bamboo = load_obj_file('models/bamboo2.obj')
        self.add_models_list(
            [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([-1,-1,-5]),scaleMatrix([.1,1,.1])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='scene') for mesh in quick_bamboo]
        )
        self.add_models_list(
            [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([-5,-1,-5]),scaleMatrix([.1,.7,.1])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='scene') for mesh in quick_bamboo]
        )
        self.add_models_list(
            [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([-6,-1,-5]),scaleMatrix([.1,1,.1])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='scene') for mesh in quick_bamboo]
        )
        self.add_models_list(
            [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([-7.5,-1,-5]),scaleMatrix([.1,.3,.1])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='scene') for mesh in quick_bamboo]
        )
        self.add_models_list(
            [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([-8,-1,-5]),scaleMatrix([.1,1,.1])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='scene') for mesh in quick_bamboo]
        )

        # The functionality of this is the same as above however a large number of these trees are drawn and the code becomes difficult to analyze. However the functionality is the same (these bamboo trees are the most complex with leaves)
        draw_bamboo_leaves(self)
        
        # Drawing the birds
        bird = load_obj_file('models/bird.obj')
        self.add_models_list(
            [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([-3,3.5,0]),scaleMatrix([.1,.1,.1])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='scene') for mesh in bird]
        )
        self.add_models_list(
            [DrawModelFromMesh(scene=self, M=np.matmul( translationMatrix([-3,2,4]),scaleMatrix([.1,.1,.1]), rotationMatrixZ(270)), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='scene') for mesh in bird]
        )
        self.add_models_list(
            [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([-3,.5,5]),scaleMatrix([.1,.1,.1])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='scene') for mesh in bird]
        )
        self.add_models_list(
            [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([-2.5,-1.2,2]),scaleMatrix([.1,.1,.1])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='scene') for mesh in bird]
        )

        # Drawing a snake
        snake = load_obj_file('models/snake.obj')
        self.add_models_list(
            [DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([1,-1,0]),scaleMatrix([.15,.15,.15])), mesh=mesh, shader=ShadowMappingShader(shadow_map=self.shadows), name='scene') for mesh in snake]
        )
        
        # Drawing a bunny
        bunny = load_obj_file('models/bunny_world.obj')
        self.bunny = DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([-3,-.5,1]), np.matmul(scaleMatrix([0.3,0.3,0.3]), rotationMatrixY(210))), mesh=bunny[0], shader=FlatShader())

        
        # Similar to the trees a large number of floor sections have to be added with the only difference being a small change in location
        draw_floor(self)

        # draw a skybox for the horizon
        self.skybox = SkyBox(scene=self)

        # showthe light on the scene
        self.show_light = DrawModelFromMesh(scene=self, M=poseMatrix(position=self.light.position, scale=0.2), mesh=Sphere(material=Material(Ka=[10,10,10])), shader=FlatShader())

        # Draw the environment and its do any environment mapping
        self.environment = EnvironmentMappingTexture(width=400, height=400)

        # this object allows to visualise the flattened cube, mainly for debugging purposes
        self.flattened_cube = FlattenCubeMap(scene=self, cube=self.environment)

    def draw_shadow_map(self):
        # first we need to clear the scene, we also clear the depth buffer to handle occlusions
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT)

        self.skybox.draw()

        for model in self.models:
            model.draw()

    # draw the reflections for the skybox and the models
    def draw_reflections(self):
        self.skybox.draw()

        for model in self.models:
            model.draw()

    def draw(self, framebuffer=False):
        '''
        Draw all models in the scene
        :return: None
        '''

        # first we need to clear the scene, we also clear the depth buffer to handle occlusions
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT)

        # when using a framebuffer, we do not update the camera to allow for arbitrary viewpoint.
        if not framebuffer:
            self.camera.update()

        # first, we draw the skybox
        self.skybox.draw()

        # render the shadows
        self.shadows.render(self)

        # when rendering the framebuffer we ignore the reflective object
        if not framebuffer:
            #glEnable(GL_BLEND)
            #glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA)
#            self.envbox.draw()
            #self.environment.update(self)
            #self.envbox.draw()

            self.environment.update(self)

            self.bunny.draw()
            # self.floor.draw()
            #self.sphere.draw()
            #glDisable(GL_BLEND)

            # if enabled, show flattened cube
            self.flattened_cube.draw()

            self.show_shadow_map.draw()

        # then we loop over all models in the list and draw them
        for model in self.models:
            model.draw()
            

        self.show_light.draw()

        # once we are done drawing, we display the scene
        # Note that here we use double buffering to avoid artefacts:
        # we draw on a different buffer than the one we display,
        # and flip the two buffers once we are done drawing.
        if not framebuffer:
            pygame.display.flip()

    def keyboard(self, event):
        '''
        Process additional keyboard events for this demo.
        '''
        Scene.keyboard(self, event)


        # translates the bunny to the right
        if event.key == pygame.K_RIGHT:
            self.translationCount +=1
            transformationX = -3 + 0.1*self.translationCount
            rotationY = 210 + 0.1*self.rotationCount
            bunny = load_obj_file('models/bunny_world.obj')
            self.bunny = DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([transformationX,-.5,1]), np.matmul(scaleMatrix([0.3,0.3,0.3]), rotationMatrixY(rotationY))), mesh=bunny[0], shader=FlatShader())
            self.bunny.draw()
            print('Moved the bunny to the right')
        # translates the bunny to the left
        if event.key == pygame.K_LEFT:
            self.translationCount -=1
            rotationY = 210 + 0.1*self.rotationCount
            transformationX = -3 + 0.1*self.translationCount
            bunny = load_obj_file('models/bunny_world.obj')
            self.bunny = DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([transformationX,-.5,1]), np.matmul(scaleMatrix([0.3,0.3,0.3]), rotationMatrixY(rotationY))), mesh=bunny[0], shader=FlatShader())
            self.bunny.draw()
            print('Moved the bunny to the left')
        # rotates the bunny 
        if event.key == pygame.K_UP:
            self.rotationCount +=1
            transformationX = -3 + 0.1*self.translationCount
            rotationY = 210 + 0.1*self.rotationCount
            bunny = load_obj_file('models/bunny_world.obj')
            self.bunny = DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([transformationX,-.5,1]), np.matmul(scaleMatrix([0.3,0.3,0.3]), rotationMatrixY(rotationY))), mesh=bunny[0], shader=FlatShader())
            self.bunny.draw()
            print('Rotated the bunny clockwise')
        if event.key == pygame.K_DOWN:
            self.rotationCount -=1
            transformationX = -3 + 0.1*self.translationCount
            rotationY = 210 + 0.1*self.rotationCount
            bunny = load_obj_file('models/bunny_world.obj')
            self.bunny = DrawModelFromMesh(scene=self, M=np.matmul(translationMatrix([transformationX,-.5,1]), np.matmul(scaleMatrix([0.3,0.3,0.3]), rotationMatrixY(rotationY))), mesh=bunny[0], shader=FlatShader())
            self.bunny.draw()
            print('Rotated the bunny anti clockwise')

        if event.key == pygame.K_c:
            if self.flattened_cube.visible:
                self.flattened_cube.visible = False
            else:
                print('--> showing cube map')
                self.flattened_cube.visible = True

        if event.key == pygame.K_t:
            if self.show_texture.visible:
                self.show_texture.visible = False
            else:
                print('--> showing texture map')
                self.show_texture.visible = True

        if event.key == pygame.K_s:
            if self.show_shadow_map.visible:
                self.show_shadow_map.visible = False
            else:
                print('--> showing shadow map')
                self.show_shadow_map.visible = True

        elif event.key == pygame.K_7:
            print('--> no face culling')
            glDisable(GL_CULL_FACE)

        elif event.key == pygame.K_8:
            print('--> glCullFace(GL_FRONT)')
            glEnable(GL_CULL_FACE)
            glCullFace(GL_FRONT)

        elif event.key == pygame.K_9:
            print('--> glCullFace(GL_BACK)')
            glEnable(GL_CULL_FACE)
            glCullFace(GL_BACK)

        elif event.key == pygame.K_BACKQUOTE:
            if glIsEnabled(GL_DEPTH_TEST):
                print('--> disable GL_DEPTH_TEST')
                glDisable(GL_DEPTH_TEST)
            else:
                print('--> enable GL_DEPTH_TEST')
                glEnable(GL_DEPTH_TEST)


if __name__ == '__main__':
    # initialises the scene object
    # scene = Scene(shaders='gouraud')
    scene = ExeterScene()

    # starts drawing the scene
    scene.run()
