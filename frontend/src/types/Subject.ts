import type { AppUser } from "./AppUser"

export type Subject = {
    code: string
    componentID: Number
    name: string
    department: string
    totalHours: string
    interestedUsers: AppUser[]
}