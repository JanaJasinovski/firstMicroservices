import { Role } from "./role";

export class User {
  userId: number | undefined;
  sub: string = "";
  firstName: string = "";
  lastName: string = "";
  password: string = "";
  roles?: Role[];
}
