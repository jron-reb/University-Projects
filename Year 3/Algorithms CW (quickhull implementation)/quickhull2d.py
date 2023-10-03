def seperate_above_and_below(p1,p2,s):
    '''
    Creates 2 sets of vertices from the set s that are seperated by the line formed by p1 and p2.
    Returns 1 set (above) represents the set of vertices above the line. The other set (below) returned represents the set of vertices below the line.
    '''
    above=[]
    below=[]
    # if the x coordinates are the same then the line is vertical
    if p1[0] == p2[0] == 0:
        return above,below

    # calculate the gradient of the line segment using (y2-y1)/(x2-x1x)
    if (p2[0] - p1[0]) == 0:
        gradient = 100000000
    else:
        gradient = (p2[1] - p1[1]) / (p2[0] - p1[0])
    # calculate the y coordinate substituting a point in the formula y = mx + c
    y_intercept = -1 * gradient * p1[0] + p1[1]

    for point in s:
        # if y > mx + c for a given point then it is above the line
        if point[1] > gradient * point[0] + y_intercept:
            above.append(point)
        # if y < mx + c for a given point then it is below the line. It is necessary to check this because if y = mx + c then it is on the line and therefore included in the convex hull and can be ignored
        elif point[1] < gradient * point[0] + y_intercept:
            below.append(point)
    return above,below

def quickhull2(p1,p2,set_segment,flag):
    '''
        The recursive function that is responsible for the majority of the computation.
        It calls itself with two points that make up a section of the convex hull (p1,p2), a segment of vertices outside the convex hull and a flag denoting whether the set is above or below the line segment formed by the 2 vertices p1,p2.
        It will eventually reach the base case, where it then returns a set of points that make up the convex hull.
    '''
    # This is the base case
    if( set_segment == [] or p1 is None or p2 is None):
        return []
    
    convex_hull = []

    # Initialise the values for the furthest distance and point
    furthest_distance=0
    furthest_point=None

    for point in set_segment:
        # uses the dot product to calculate the distance between a line and a point
        v1=p1[1]-p2[1]
        v2=p2[0]-p1[0]
        v3=p1[0]*p2[1]-p2[0]*p1[1]
        distance = abs(( v1 * point [0] + v2 * point [1] + v3)/(( v1 * v1 + v2 * v2 ) ** 0.5))
       
        # if the distance between the line and a point is greater than the current furthest point, then record the new furthest point       
        if(distance>furthest_distance):
            furthest_distance=distance
            furthest_point=point

    # add the new furthest point to the set of convex_hull vertices
    convex_hull.append(furthest_point)
    # the furthest point can be removed because it is now part of the convex hull
    set_segment.remove(furthest_point)

    p1above,p1below = seperate_above_and_below(p1,furthest_point,set_segment)
    p2above,p2below = seperate_above_and_below(p2,furthest_point,set_segment)

    # The function calls itself on each segment. Only use either the segment above or below the line formed. This is because only one set will be outsied the convex hull whereas the other set will be contained within the convex hull.
    if flag=='above':
        convex_hull.append(quickhull2(p1,furthest_point,p1above,'above'))
        convex_hull.append(quickhull2(furthest_point,p2,p2above,'above'))
    else:
        convex_hull.append(quickhull2(p1,furthest_point,p1below,'below'))
        convex_hull.append(quickhull2(furthest_point,p2,p2below,'below'))
    return convex_hull

def quickhull(s):
    '''
        Calculate the convex hull of a set of vertices.
        S is a set of tuples (x,y) that represent the x and y coordinates of points.
        The function returns the set of vertices that make up the convex hull of the set s.
    '''
    # If the set is less than 2 points long then there can be no convex set.
    if len(s) <= 2:
        print('At least 2 points required to construct a convex hull')
        return s

    convex_hull = []

    # sorts the set from smallest to largest based first on the x coordinate and then the y coordinate. 
    sorted_set = sorted(s,key=lambda x:(x[0],x[1]))
    minx = sorted_set[0]
    maxx = sorted_set[-1]
    # The minimum and maximum x values can be deleted from the set because they will be part of the convex hull
    del sorted_set[0]
    del sorted_set[-1]
    # Add the values to the convex hull
    convex_hull.append(minx)
    convex_hull.append(maxx)
    
    # Create 2 sets, 1 representing points above the line and one below
    above, below = seperate_above_and_below(minx, maxx, sorted_set)

    # Call the recursive part of the algorithm seperately with each set
    quickhull2(minx, maxx, above, "above")
    quickhull2(minx, maxx, below, "below")
    return convex_hull

points = [(1,1), (1,0), (0,0), (0,1)]
print (quickhull(points))