import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom';
import axiosInstance from '../api/axios';



export default function Register() {
    const navigate = useNavigate();
    const [form, setForm] = useState({
        fullName: "",
        email: "",
        password: "",
        role: "SENIOR"  // Default
    });

    const handleChange = (e) => {
        setForm({ ...form, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await axiosInstance.post("/users/register", form);
            navigate("/login");
        } catch (err) {
            alert("Registration failed");
            console.error(err);
        }
    };

    return (
        <div className='min-h-screen flex items-center justify-center bg-gray-100'>
            <form onSubmit={handleSubmit} className='bg-white p-6 rounded shadow-md w-80'>
                <h2 className='text-2xl font-semibold mb-4'>Register</h2>

                <input
                    name="fullName"
                    type="text"
                    placeholder="Full Name"
                    className="mb-3 w-full border px-3 py-2 rounded"
                    value={form.fullName}
                    onChange={handleChange}
                    required
                />

                <input
                    name="email"
                    type="email"
                    placeholder='Email'
                    className='mb-3 w-full border px-3 py-2 rounded'
                    value={form.email}
                    onChange={handleChange}
                    required
                />

                <input
                    name='password'
                    type='password'
                    placeholder='Password'
                    className='mb-3 w-full border px-3 py-2 rounded'
                    value={form.password}
                    onChange={handleChange}
                    required
                />

                <select
                    name="role"
                    className='mb-4 w-full border px-3 py-2 rounded'
                    value={form.role}
                    onChange={handleChange}
                >
                    <option value="SENIOR">Senior</option>
                    <option value="CAREGIVER">Caregiver</option>
                </select>

                <button type="submit" className='w-full bg-blue-500 text-white py-2 rounded hover:bg-blue-600'>
                    Register
                </button>
            </form>
        </div>
    );
}
