class Single_Var_Function:
    """
    A mathematical function object, in the form f(x) = so and so.
    func(function): a continuously differentiable function which takes one numerical argument."""

    def __init__(self, func):
        self.func = func

    def value_at(self, x):
        """
        Protects the program against divisions by 0.
        You can still use functions like f(x) = 1/x, just keep 0 out of your domain."""

        return_val = 0
        try:
            return_val = self.func(x)
        except ZeroDivisionError:
            print("Your function is not continuous!")  # need I remind you?
        return return_val

    def aroc(self, x1, x2):
        """Average rate of change"""

        if x1 == x2:
            return 0
        elif x2 < x1:
            x1, x2 = x2, x1

        return (self.value_at(x2) - self.value_at(x1)) / (x2 - x1)

    def derivative_at(self, x):
        """A symmetric difference quotient with a very small difference."""
        return self.aroc(x - .000001, x + .000001)

    def derivative(self):
        return Single_Var_Function(lambda x: self.derivative_at(x))

    def mid_rectangle(self, x1, x2):
        length = x2 - x1
        height = self.value_at(x1 + (x2 - x1) / 2)
        return length * height

    def left_rectangle(self, x1, x2):
        length = x2 - x1
        height = self.value_at(x1)
        return length * height

    def right_rectangle(self, x1, x2):
        length = x2 - x1
        height = self.value_at(x2)
        return length * height;

    def left_riemann(self, x1, x2, steps):
        """
        Underestimates when the function is increasing, overestimates when the function is decreasing.
        More steps lead to greater accuracy. """

        interval = (x2 - x1) / steps
        total = 0
        for s in range(0, steps):
            total += self.left_rectangle(x1 + (s * interval), x1 + ((s + 1) * interval))
        return total

    def right_riemann(self, x1, x2, steps):
        """
        Overestimates when the function is increasing, underestimates when the function is decreasing.
        More steps leads to greater accuracy."""

        interval = (x2 - x1) / steps
        total = 0
        for s in range(0, steps):
            total += self.right_rectangle(x1 + (s * interval), x1 + ((s + 1) * interval))
        return total

    def mid_riemann(self, x1, x2, steps):
        """
        Probably your most accurate choice for a definite integral."""
        interval = (x2 - x1) / steps
        total = 0
        for s in range(0, steps):
            total += self.mid_rectangle(x1 + (s * interval), x1 + ((s + 1) * interval))
        return total

    def length(self, x1, x2):
        return pow((pow((x2 - x1), 2) + pow(self.value_at(x2) - self.value_at(x1), 2)), 0.5)

    def arc_length(self, x1, x2, steps):
        """Calculates the length of your curve."""
        interval = (x2 - x1) / steps
        total = 0
        for s in range(0, steps):
            total += self.length(x1 + s * interval, x1 + (s + 1) * interval)
        return total

    def surface_of_revolution(self, x1, x2, steps):
        """Calculates the surface area if your curve revolved around the x axis."""

        interval = (x2 - x1) / steps
        total = 0
        for s in range(0, steps):
            total += self.length(x1 + s * interval, x1 + (s + 1) * interval) * self.value_at(x1 + s * interval)
        return total * 2 * 3.14159

    def volume_of_revolution(self, x1, x2, steps):
        """Calculates the volume if your curve revolved around the x axis."""

        interval = (x2 - x1) / steps
        total = 0
        for s in range(0, steps):
            total += interval * pow(self.value_at(x1 + s * interval), 2)
        return total * 3.14159


class Two_Var_Function:
    """A function in the form f(x, y) = so and so. So far, we only have partial derivatives for this one."""

    def __init__(self, xyfunc):
        self.xyfunc = xyfunc

    def df_dx_at(self, x, y):
        constant_y = Single_Var_Function(lambda a: (self.value_at(a, y)))
        return constant_y.derivative_at(x)

    def df_dx(self):
        return lambda x, y: self.df_dx_at(x, y)

    def df_dy(self):
        return lambda x, y: self.df_dy_at(x, y)

    def df_dy_at(self, x, y):
        constant_x = Single_Var_Function(lambda a: (self.value_at(x, a)))
        return constant_x.derivative_at(y)

    def value_at(self, x, y):
        """
        Protects the program against divisions by 0.
        You can still use functions like f(x,y) = 1/xy; just keep (0,0) out of your domain."""

        return_val = 0
        try:
            return_val = self.xyfunc(x, y)
        except ZeroDivisionError:
            print("Your function doesn't seem to exist at that point.")  # We're doing our best here.
        return return_val


print()
line = Single_Var_Function(lambda x: x)
print("f(x) = x")
print(f"Derivative at 3: {line.derivative_at(3)}")
print(f"Mid riemann with three steps from 0 to 3: {line.mid_riemann(0, 3, 3)}")
print(f"Left riemann with thirty steps from 0 to 3: {line.mid_riemann(0, 3, 30)}")
print(f"Arc length from x = 0 to x = 3: {line.arc_length(0, 3, 20)}")
print(f"Surface area of right cone with radius 3:{line.surface_of_revolution(0, 3, 90)}")
print(f"Volume of right cone with radius 3: {line.volume_of_revolution(0, 3, 90)}")
print()

quad = Single_Var_Function(lambda x: pow(x, 2))
print("f(x) = x^2")
print(f"Derivative at x = 3: {quad.derivative_at(3)}")
print(f"Mid riemann from 0 to 3 with thirty steps: {quad.mid_riemann(0, 3, 30)}")
print(f"Arc length from 0 to 3: {quad.arc_length(0, 3, 30)}")
print()

y_x_squared = Two_Var_Function(lambda x, y: y * pow(x, 2))
print("f(x,y) = yx^2")
print(y_x_squared.df_dx_at(1, 2))
print(y_x_squared.df_dy_at(2, 0))
print()

y_squared_x_squared = Two_Var_Function(lambda x, y: pow(y, 2) * pow(x, 2))
print("f(x,y) = y^2 * x^2")
print(y_squared_x_squared.df_dx_at(3, 2))
