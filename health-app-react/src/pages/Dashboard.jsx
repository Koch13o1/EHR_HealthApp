import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../auth/AuthContext";
import axiosInstance from "../api/axios";

export default function Dashboard() {
    const { logout } = useAuth();
    const navigate = useNavigate();
    const [user, setUser] = useState(null);

    useEffect(() => {
        axiosInstance
            .get("/users/me")
            .then((res) => setUser(res.data))
            .catch((err) => {
                console.error("Token may be invalid", err);
                logout();
                navigate("/login");
            });
    }, []);

    if (!user) return <div className="p-6">Loading...</div>;

    return (
        <div className="min-h-screen flex flex-col items-center justify-center bg-gray-50 text-center">
            <h1 className="text-3xl font-bold mb-2">Welcome, {user.fullName}</h1>
            <p className="mb-2">Email: {user.email}</p>
            <p className="mb-4">Role: {user.role}</p>

            <div className="flex gap-4">
                <button
                    onClick={() => navigate("/vitals")}
                    className="px-4 py-2 bg-green-500 text-white rounded hover:bg-green-600"
                >
                    Log/View Vitals
                </button>

                <button
                    onClick={() => {
                        logout();
                        navigate("/login");
                    }}
                    className="px-4 py-2 bg-red-500 text-white rounded hover:bg-red-600"
                >
                    Logout
                </button>
            </div>
        </div>
    );
}