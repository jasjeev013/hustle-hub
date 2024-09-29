export interface Task {
    id: number;
    title: string;
    description: string;
    due_date: Date; // You can keep this as Date if you handle parsing appropriately
    priority: string;
    status: string;
    categories: Array<any>; // Adjust the type based on what you expect for categories
}

export interface Category {
    id: number;
    name: string;
    color: string;
    tasks: Task[]; // Use the Task interface here
}

export class LoginDetails{
    username: String;
    password: String;

    constructor() {
        this.username = '';
        this.password = '';
    }
}

export class UserRegisterDetails{
    username: String;
    password: String;
    email: String;

    constructor() {
        this.username = '';
        this.password = '';
        this.email = '';
    }
}

export class ProfileRegisterDetails{
    firstName: String;
    lastName: String;
    profileImageURL: String;
    bio: String;

    constructor() {
        this.firstName = '';
        this.lastName = '';
        this.profileImageURL = '';
        this.bio = '';
    }
}

export interface LoginResponse{
    status:string
    jwtToken:string
}

export interface ApiResponseObject {
    message:String,
    result:Boolean,
    object:any
}
export interface ApiResponseData {
    message:String,
    result:Boolean,
    data: Array<any>
}
