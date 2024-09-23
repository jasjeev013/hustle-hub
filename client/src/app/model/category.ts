export class Category {
    name: String;
    tasks: Array<{
        taskId: Number,
        title: String,
        description: String,
        dueDate: Date,
        priority: String,
        status: String
    }>;

    constructor() {
        this.name = '';
        this.tasks = [];
    }
}

export class LoginDetails{
    email: String;
    password: String;

    constructor() {
        this.email = '';
        this.password = '';
    }
}
export interface LoginResponse{
    status:String
    jwtToken:String
}
