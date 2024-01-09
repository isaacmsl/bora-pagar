import type { AppUser } from "./AppUser"

export type Subject = {
    componentID: Number
    name: string
    department: string
    totalHours: string
    interestedUsers: AppUser[]
}